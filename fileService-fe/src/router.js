import React from 'react'
import PropTypes from 'prop-types'
import { Route, Switch, Redirect, routerRedux } from 'dva/router'
import { LocaleProvider } from 'antd'
import zh_CN from 'antd/lib/locale-provider/zh_CN'
import 'moment/locale/zh-cn'
import dynamic from 'dva/dynamic'
import App from 'routes/app'

const { ConnectedRouter } = routerRedux

const Routers = function ({ history, app }) {
  const error = dynamic({
    app,
    component: () => import('./routes/error'),
  })
  const routes = [
   
    {
      path: '/user',
      models: () => [import('./models/user')],
      component: () => import('./routes/user/'),
    },
    
  ]

  return (
    <ConnectedRouter history={history}>
      <LocaleProvider locale={zh_CN}>
        <App>
          <Switch>
            <Route exact path="/" render={() => (<Redirect to="/dashboard" />)} />
            {
              routes.map(({ path, ...dynamics }, key) => (
                <Route key={key} exact path={path} component={dynamic({ app, ...dynamics })} />
              ))
            }
            <Route component={error} />
          </Switch>
        </App>
      </LocaleProvider>
    </ConnectedRouter>
  )
}

Routers.propTypes = {
  history: PropTypes.object,
  app: PropTypes.object,
}

export default Routers
