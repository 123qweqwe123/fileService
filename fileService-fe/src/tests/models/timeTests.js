/**
 * Created by huangrupeng on 17/6/25.
 */
//var valid = (new Date(timestamp)).getTime() > 0;
//
// var valid = (new Date(14)).getTime() > 0; // true
// console.log(valid)
// var valid = (new Date('abc')).getTime() > 0; // false
// console.log(valid)
// console.log(new Date().getTime())

var m = require('moment')

var b = m(1494432000001)
var d = b.format('YYYY-MM-DD HH:mm:ss')
console.log(d)

var c = d.replace('00:00:00', "")
console.log(c)
