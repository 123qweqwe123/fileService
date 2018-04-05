import 'babel-polyfill'
import dva from 'dva'
import createLoading from 'dva-loading'
import createHistory from 'history/createBrowserHistory'
import { message } from 'antd'

const history = createHistory({
  basename: '/file', // 这里放入你对应的 basename
})

// 1. Initialize
const app = dva({
  ...createLoading({
    effects: true,
  }),
  history,
  onError (error) {
    console.log(error)
    if (error.message) {
      message.error(error.message, 3)
    }
  },
})

// 2. Model
app.model(require('./models/app'))

// 3. Router
app.router(require('./router'))

// 4. Start
app.start('#root')
