// pages/home/home.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    prices: [],
    factoryType: "煤炭"
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // 查询煤价
    var params = {
      factoryType: this.data.factoryType
    };
    this.getProductPriceList(params);
  },
  getProductPriceList: function (data) {
    var that = this;
    wx.request({
      url: 'https://coalapp.smmeitan.cn/coalPrice/query',
      data: data,
      method: "POST",
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        that.setData({
          prices: res.data.data.data
        });
      }
    })

  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },

  gotoDetail: function (e) {
    wx.navigateTo({
      url: '/pages/index/index?data=' + e.currentTarget.id
    })
  },
  gotoLogistics: function (e) {
    wx.navigateTo({
      url: '/pages/logistics/logistics?data=' + e.currentTarget.id
    })
  },

  gotoDetailPage: function (e) {
    wx.navigateTo({
      url: '/pages/logs/logs?data=' + JSON.stringify(e.currentTarget.dataset.item)
    })
  }
  
})