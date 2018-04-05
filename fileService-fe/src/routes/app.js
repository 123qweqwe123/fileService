import React from 'react'
import PropTypes from 'prop-types'
import pathToRegexp from 'path-to-regexp'
import NProgress from 'nprogress'
import { connect } from 'dva'
import { BackTop, Layout } from 'antd'
import { Helmet } from 'react-helmet'
import { withRouter } from 'dva/router'
import { MyLayout, Loader } from 'components'
import { classnames, config } from 'utils'
import '../themes/index.less'
import './app.less'

const { prefix, openPages } = config
const { Content, Footer, Sider } = Layout
const { Header, Bread, styles } = MyLayout
let { iconFontJS, iconFontCSS, logo } = config
// 这个地方只有生产环境才会加上 prefix
iconFontJS = BASE_PREFIX + iconFontJS
iconFontCSS = BASE_PREFIX + iconFontCSS
logo = BASE_PREFIX + logo

let lastHref

const App = ({
  children, location, dispatch, app, loading,
}) => {
  const {
    user, siderFold, darkTheme, isNavbar, menuPopoverVisible, navOpenKeys, menu,
  } = app
  let { pathname } = location
  pathname = pathname.startsWith('/') ? pathname : `/${pathname}`
  const href = window.location.href


  if (lastHref !== href) {
    NProgress.start()
    if (!loading.global) {
      NProgress.done()
      lastHref = href
    }
  }

  const headerProps = {
    menu,
    user,
    siderFold,
    location,
    isNavbar,
    menuPopoverVisible,
    navOpenKeys,
    switchMenuPopover () {
      dispatch({ type: 'app/switchMenuPopver' })
    },
    logout () {
      dispatch({ type: 'app/logout' })
    },
    switchSider () {
      dispatch({ type: 'app/switchSider' })
    },
    changeOpenKeys (openKeys) {
      dispatch({ type: 'app/handleNavOpenKeys', payload: { navOpenKeys: openKeys } })
    },
  }

  const siderProps = {
    menu,
    siderFold,
    darkTheme,
    location,
    navOpenKeys,
    changeTheme () {
      dispatch({ type: 'app/switchTheme' })
    },
    changeOpenKeys (openKeys) {
      window.localStorage.setItem(`${prefix}navOpenKeys`, JSON.stringify(openKeys))
      dispatch({ type: 'app/handleNavOpenKeys', payload: { navOpenKeys: openKeys } })
    },
  }

  const breadProps = {
    menu,
    location,
  }

  if (openPages && openPages.includes(pathname)) {
    return (<div>
      <Loader fullScreen spinning={loading.effects['app/query']} />
      {children}
    </div>)
  }

  return (
    <div>
      <Loader fullScreen spinning={loading.effects['app/query']} />
      <Helmet>
        <title>文件服务平台</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href={logo} type="image/x-icon" />
        {iconFontJS && <script src={iconFontJS} />}
        {iconFontCSS && <link rel="stylesheet" href={iconFontCSS} />}
      </Helmet>

      <Layout className={classnames({ [styles.dark]: darkTheme, [styles.light]: !darkTheme })}>
        {!isNavbar && <Sider
          trigger={null}
          collapsible
          collapsed={siderFold}
        >
          {siderProps.menu.length === 0 ? null : <MyLayout.Sider {...siderProps} />}
        </Sider>}
        <Layout style={{ height: '100vh', overflow: 'scroll' }} id="mainContainer">
          <BackTop target={() => document.getElementById('mainContainer')} />
          <Header {...headerProps} />
          <Content>
            <Bread {...breadProps} />
            { children }
          </Content>
          <Footer >
            {config.footerText}
          </Footer>
        </Layout>
      </Layout>
    </div>
  )
}

App.propTypes = {
  children: PropTypes.element.isRequired,
  location: PropTypes.object,
  dispatch: PropTypes.func,
  app: PropTypes.object,
  loading: PropTypes.object,
}

export default withRouter(connect(({ app, loading }) => ({ app, loading }))(App))
