/**
 * Created by huangrupeng on 17/6/8.
 */
var qs = require('qs')

// https://github.com/ljharb/qs

// 加上 indices 参数，防止后台接收数组参数异常
var a = qs.stringify({ a: ['b', 'c', 'd'] }, { indices: false });
var b = qs.stringify({ a: ['b', 'c', 'd'] });
console.log(a)  // a=b&a=c&a=d
console.log(b)  // a%5B0%5D=b&a%5B1%5D=c&a%5B2%5D=d
console.log(qs.stringify('a=b&a=c&a=d'))

var qs = require('qs');
var assert = require('assert');

var obj = qs.parse('a=c&b=d');
assert.deepEqual(obj, { a: 'c', b: 'd' });

var str = qs.stringify(obj);
assert.equal(str, 'a=c&b=d');
