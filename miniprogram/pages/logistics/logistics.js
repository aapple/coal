
Page({
  data: {
    lists: []
  },
  onLoad: function (options) {
    this.getList();
  },
  getList: function () {
    var that = this;
    wx.request({
      url: 'https://coalapp.smmeitan.cn/logistics/query',
      data: {},
      method: "POST",
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        that.setData({
          lists: res.data.data.data
        });
      }
    })

  },
  gotoDetail: function (e) {
    wx.navigateTo({
      url: '/pages/logisticsDetail/logisticsDetail?data=' + JSON.stringify(e.currentTarget.dataset.item)
    })
  } 
})
