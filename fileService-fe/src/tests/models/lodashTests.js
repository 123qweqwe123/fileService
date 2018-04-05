/**
 * Created by huangrupeng on 17/6/25.
 */
const _ = require('lodash')

let a = null;
let b = ''

console.log(_.eq(a, b))

console.log(_.isEmpty(undefined))

let c = [
  {
    id: 11,
    name: '小明'
  },
  {
    id: 22,
    name: '小红'
  }, {
    id: 33,
    name: '小明'
  }, {
    id: 11,
    name: '小红1'
  }
]
// 数组去重，指定比较器
let d = _.uniqWith(c, (r, d) => r.name === d.name)
console.log(d)

// 判断是否有指定的 key
let e = { x: 1, y: 2, 3: null }
console.log(_.has(e, '3'))

let f = {}
console.log(_.isEmpty(f))


let g = [
  {
    id: 1,
    name: '小红'
  },
  {
    id: 2,
    name: '小黑'
  },
  {
    id: 3,
    name: '小李'
  }
]
let h = ['小红', '小黑']
// filter 过滤所有元素
console.log(_.filter(g, x => _.includes(h, x.name)))
// find 只会找第一个元素
console.log(_.find(g, x => _.includes(h, x.name)))


// 判断对象是否包含某个属性
let i = { field1: { name: 'xiaoming', value: undefined } }
console.log(_.has(i, 'field1.value'))


// 将 targetKeys 里面的 moveKeys 删除
// target = lodash.pullAll(targetKeys, moveKeys)


let k = [
  { lccCode: 1101, count: 10 },
  { lccCode: 1101, count: 2 },
  { lccCode: 1202, count: 3 },
]
let l = _.groupBy(k, x => {
  x.lccCode
})
console.log(l)

let m = ["b0841170bc5b410bba7f3532860bab02"]
let n = 'b0841170bc5b410bba7f3532860bab02'
console.log(_.includes(m, n))


const arr1 = [{id: 1, name: 'xiaoming'}, {id: 2, name: 'xiaohei'}]
const arr2 = [{id: 1, name: 'xiaoli'}, {id: 3, name: 'xiaohua'}]

console.log(_.unionWith(arr1, arr2, (x1, x2) => x1.id === x2.id))
