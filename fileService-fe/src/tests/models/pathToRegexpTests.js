/**
 * Created by huangrupeng on 17/6/21.
 */
var pathToRegexp = require('path-to-regexp')

// Turn an Express-style path string such as /user/:name into a regular expression.
// http://forbeslindesay.github.io/express-route-tester/

//const match = pathToRegexp.parse(url)
//url = pathToRegexp.compile(url)(data)

const url = '/user/:id'
const res = pathToRegexp.compile(url)({ id: 22 })  // 得到 /user/22

var keys = []
var re = pathToRegexp('/foo/:bar', keys)
console.log('======')
for (let x of keys) {
  console.log(x)
}
console.log('======')
console.log(re.exec('/foo/route'))

console.log('=========')
const match = pathToRegexp.parse(url)
for (let item of match) {
  console.log(item)
}
