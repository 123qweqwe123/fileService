module.exports = {
  name: '文件服务平台',
  version: '0.0.1',
  prefix: 'file',
  footerText: 'Copyright © 2017. 量码博信 . All rights reserved',
  logo: '/logo.svg',
  qrCode: '/QR_code.jpg',
  iconFontCSS: '/iconfont.css',
  iconFontJS: '/iconfont.js',
  CORS: [],
  openPages: ['/user'],
  api: {
   
  
    users: '/common/file/query',
    //download: '',
    download: `/common/file/download`,
    preview: '/common/file/preview',
    common: {
      autoComplete: '/common/combox/autoComplete',
      select: '/common/combox/select',
      upload: '/common/file/upload',
      preview: '/findtest/preview',
      download: `/findtest/download`,
      //download: '/common/file/download',
      downloadTemplate: '/common/file/template',
    },
  }
}
