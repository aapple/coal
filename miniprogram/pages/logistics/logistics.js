
Page({
  data: {
    lists: [],
    start: ""
  },
  onLoad: function (options) {
    this.getList();
  },
  getList: function () {
    var that = this;
    var data = {
      start: this.data.start
    };
    wx.request({
      url: 'https://coalapp.smmeitan.cn/logistics/query',
      data: data,
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
  clearInput: function () {
    this.setData({
      start: ""
    });

    this.getList();
  },
  inputTyping: function (e) {
    this.setData({
      start: e.detail.value
    });
  },
  searchPrice: function () {
    this.getList();
  },
  gotoDetail: function (e) {
    wx.navigateTo({
      url: '/pages/logisticsDetail/logisticsDetail?data=' + JSON.stringify(e.currentTarget.dataset.item)
    })
  } 
})
