//logs.js
const util = require('../../utils/util.js')

Page({
  data: {
    detail: {},
    priceType: ""
  },
  onLoad: function (options) {
    
    // 初始化数据
    var item = JSON.parse(options.data)
    
    if (item.priceOwnerType == 1) {
      var priceType = "两票价";
    } else {
      var priceType = "一票价";
    }


    this.setData({
      detail: item,
      priceType: priceType
    })
  },
  gotoCall() {
    if (this.data.detail.priceOwnerType == 1) {
      wx.makePhoneCall({
        phoneNumber: this.data.detail.factory.onwerCallNumber
      })  
    } else {
      wx.makePhoneCall({
        phoneNumber: this.data.detail.factory.salerCallNumber
      }) 
    }
  }
})
