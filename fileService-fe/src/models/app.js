import queryString from 'query-string'
import { routerRedux } from 'dva/router'
import { parse } from 'qs'
import { config } from '../utils'
import { query, logout } from '../services/app'

const { prefix, api } = config
const APP_NAME = BASE_PREFIX

const { download } = api


export default {
  namespace: 'app',
  state: {
    user: {},
    menu: [],
    menuPopoverVisible: false,
    siderFold: localStorage.getItem(`${prefix}siderFold`) === 'true',
    darkTheme: localStorage.getItem(`${prefix}darkTheme`) === 'true',
    isNavbar: document.body.clientWidth < 769,
    navOpenKeys: JSON.parse(localStorage.getItem(`${prefix}navOpenKeys`)) || [],
    locationPathname: '',
    locationQuery: {},
    parameters: {}, // 参数字典
  },
  subscriptions: {
    setupHistory ({ dispatch, history }) {
      history.listen((location) => {
        dispatch({
          type: 'updateState',
          payload: {
            locationPathname: location.pathname,
            locationQuery: queryString.parse(location.search),
          },
        })
      })
    },
    setup ({ dispatch }) {
      dispatch({ type: 'query' })
      let tid
      window.onresize = () => {
        clearTimeout(tid)
        tid = setTimeout(() => {
          dispatch({ type: 'changeNavbar' })
        }, 300)
      }
    },

  },
  effects: {

    * query ({
      payload,
    }, { call, put, select }) {
     
      
        yield put(routerRedux.push({
          pathname: '/user',
          // search: queryString.stringify({
          //   from: locationPathname,
          // }),
        }))
      
    },

    * logout ({
      payload,
    }, { call, put }) {
      const data = yield call(logout, parse(payload))
      if (data.success) {
        yield put({ type: 'query' })
      } else {
        throw (data.result.message)
      }
    },

    * changeNavba (action, { put, select }) {
      const { app } = yield (select(_ => _))
      const isNavbar = document.body.clientWidth < 769
      if (isNavbar !== app.isNavbar) {
        yield put({ type: 'handleNavbar', payload: isNavbar })
      }
    },
    // 查询字典，字典被缓存
    * queryDict ({ payload }, { call, put, select }) {
      const { app } = yield (select(_ => _))
      const { parameters } = app
      const { paraType } = payload
      // 可以同时设置多个 paraType
      const paraTypeArr = paraType.split(',')
      const paramsByType = {}
      for (let i = 0; i < paraTypeArr.length; i += 1) {
        let currParaType = paraTypeArr[i]
        let parameterByType = parameters[currParaType]
        if (!parameterByType) {
          const data = yield call(querySelect, { paraType: currParaType })
          if (data.success) {
            paramsByType[currParaType] = data.array
          } else {
            throw (data.result.message)
          }
        }
      }
      yield put({ type: 'queryDictSuccess', payload: { paramsByType } })
    },

  },
  reducers: {
    updateState (state, { payload }) {
      return {
        ...state,
        ...payload,
      }
    },
    querySuccess (state, { payload: data }) {
      const { user, menu } = data
      return {
        ...state,
        user,
        menu,
      }
    },

    switchSider (state) {
      localStorage.setItem(`${prefix}siderFold`, !state.siderFold)
      return {
        ...state,
        siderFold: !state.siderFold,
      }
    },

    switchTheme (state) {
      localStorage.setItem(`${prefix}darkTheme`, !state.darkTheme)
      return {
        ...state,
        darkTheme: !state.darkTheme,
      }
    },

    switchMenuPopver (state) {
      return {
        ...state,
        menuPopoverVisible: !state.menuPopoverVisible,
      }
    },

    handleNavbar (state, { payload }) {
      return {
        ...state,
        isNavbar: payload,
      }
    },

    handleNavOpenKeys (state, { payload: navOpenKeys }) {
      return {
        ...state,
        ...navOpenKeys,
      }
    },

    download (state, { payload }) {
      const { fileid } = payload
      window.location.href = `${APP_NAME}${download}?fileid=${encodeURIComponent(fileid)}`
      return {
        ...state,
      }
    },

    downloadTemplate (state, { payload }) {
      const { tempName } = payload
      window.open(`${APP_NAME}${downloadTemplate}?tempName=${encodeURIComponent(tempName)}`)
      return {
        ...state,
      }
    },

    queryDictSuccess (state, { payload }) {
      const { paramsByType } = payload
      let { parameters } = state
      parameters = { ...parameters, ...paramsByType }
      return {
        ...state,
        parameters,
      }
    },
  },
}
