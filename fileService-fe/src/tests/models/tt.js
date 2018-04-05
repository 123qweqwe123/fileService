/**
 * Created by huangrupeng on 17/7/2.
 */
let a = [
  {
    value: 1,
    text: 'sfdsdf'
  },
  {
    value: 2,
    text: 'sdsdfsdf'
  }
]

let b = a.forEach(x => {
  x.text = x.value + x.text
})
console.log(b)
