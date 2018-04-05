/**
 * 将数组转化为tree结构，根节点的id不能为0
 * Created by huangrupeng on 17/5/29.
 */
var arrayToTree = require('array-to-tree');

var a = [{
  "id": 1,
  "name": "root",
  "code": null,
  "parentId": null,
  "permissionLevel": null,
  "isLeaf": null,
  "menuId": null,
  "createBy": null,
  "createTime": null,
  "updateBy": null,
  "updateTime": null,
  "remark": null,
  "isDelete": null,
  "isResource": null
}, {
  "id": 2,
  "name": "系统管理",
  "code": "sys",
  "parentId": 1,
  "permissionLevel": 1,
  "isLeaf": null,
  "menuId": null,
  "createBy": null,
  "createTime": null,
  "updateBy": null,
  "updateTime": null,
  "remark": null,
  "isDelete": null,
  "isResource": null
}, {
  "id": 3,
  "name": "用户管理",
  "code": "sys:user",
  "parentId": 2,
  "permissionLevel": 2,
  "isLeaf": null,
  "menuId": null,
  "createBy": null,
  "createTime": null,
  "updateBy": null,
  "updateTime": null,
  "remark": null,
  "isDelete": null,
  "isResource": null
}, {
  "id": 4,
  "name": "管理控制台",
  "code": "dashboard",
  "parentId": 1,
  "permissionLevel": 1,
  "isLeaf": null,
  "menuId": null,
  "createBy": null,
  "createTime": null,
  "updateBy": null,
  "updateTime": null,
  "remark": null,
  "isDelete": null,
  "isResource": null
}]


arrayToTree(a, { parentProperty: 'parentId' })

var menuPermissionTree = [{
  "key": "10",
  "value": "10",
  "label": "管理控制台",
  "parentId": 1
}, { "key": "2", "value": "2", "label": "系统管理", "parentId": 1 }, {
  "key": "14",
  "value": "14",
  "label": "账户管理",
  "parentId": 2
}, { "key": "3", "value": "3", "label": "用户管理", "parentId": 2 }]
// 需要制定 parentProperty, customID, rootID:
//console.log(arrayToTree(menuPermissionTree, {parentProperty: 'parentId', customID: 'key', rootID: 1}))


var aa = [{
  "id": 2,
  "bpid": null,
  "mpid": 1,
  "name": "管理控制台",
  "icon": "laptop",
  "router": "/dashboard",
  "permission": 10,
  "sequence": 1,
  "createTime": null,
  "createBy": null,
  "updateTime": 1496305833000,
  "updateBy": "sys",
  "menuLevel": 2,
  "helpCode": null
}, {
  "id": 4,
  "bpid": 3,
  "mpid": 3,
  "name": "账户管理",
  "icon": null,
  "router": "/sys/account",
  "permission": 3,
  "sequence": 1,
  "createTime": null,
  "createBy": null,
  "updateTime": 1496317285000,
  "updateBy": "sys",
  "menuLevel": 3,
  "helpCode": null
}, {
  "id": 5,
  "bpid": 3,
  "mpid": 3,
  "name": "角色管理",
  "icon": null,
  "router": "/sys/role",
  "permission": 15,
  "sequence": 2,
  "createTime": null,
  "createBy": null,
  "updateTime": 1496312393000,
  "updateBy": "sys",
  "menuLevel": 3,
  "helpCode": null
}, {
  "id": 6,
  "bpid": 3,
  "mpid": 3,
  "name": "权限管理",
  "icon": null,
  "router": "/sys/permission",
  "permission": 16,
  "sequence": 3,
  "createTime": null,
  "createBy": null,
  "updateTime": 1496311824000,
  "updateBy": "sys",
  "menuLevel": 3,
  "helpCode": null
}, {
  "id": 7,
  "bpid": 3,
  "mpid": 3,
  "name": "菜单管理",
  "icon": null,
  "router": "/sys/menu",
  "permission": 17,
  "sequence": 4,
  "createTime": null,
  "createBy": null,
  "updateTime": 1496312526000,
  "updateBy": "sys",
  "menuLevel": 3,
  "helpCode": null
}, {
  "id": 3,
  "bpid": 2,
  "mpid": 1,
  "name": "系统管理",
  "icon": "setting",
  "router": null,
  "permission": 2,
  "sequence": 2,
  "createTime": null,
  "createBy": null,
  "updateTime": 1496839111000,
  "updateBy": "sys",
  "menuLevel": null,
  "helpCode": null
}]

//console.log(arrayToTree(aa, { parentProperty: 'mpid', rootID: 1 }))


let bb = aa.map(x => {
  if (x.mpid = 1) {
    x.bpid = -1
  }
  return x
})
console.log('----')
console.log(bb)
