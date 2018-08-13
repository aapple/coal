//logs.js
const util = require('../../utils/util.js')

Page({
  data: {
    detail: {},
    factory: {}
  },
  onLoad: function (options) {
    
    // 初始化数据
    var item = JSON.parse(options.data)

    this.setData({
      detail: item
    });

    this.getFactoryDetail();
  },    
  getFactoryDetail: function () {
    var that = this;
    wx.request({
      url: 'https://coalapp.smmeitan.cn/factory/query',
      data: { "factoryName": this.data.detail.factoryName},
      method: "POST",
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        that.setData({
          factory: res.data.data.data[0]
        });
      }
    })
 
  },
  gotoCall() {
    wx.makePhoneCall({
      phoneNumber: this.data.factory.sallerPhone
    }) 
  }
})
