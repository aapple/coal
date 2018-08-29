
var sliderWidth = 60;

Page({
  data: {
    inputShowed: false,
    factoryName: "",
    prices: [],
    tabs: ["面煤","块煤","籽煤","原煤","混煤"],
    activeIndex: 0,
    sliderOffset: 0,
    sliderLeft: 0,
    productType: "面煤",
    factoryType: "煤炭",
  },
  onLoad: function (options) {

    // 初始化数据
    this.setData({
      factoryType: options.data
    })

    if (options.data == '兰炭'){
      this.setData({
        tabs: ["大料", "中料", "小料",  "焦面"],
        productType: "大料"
      })
    }

    var that = this;

    // 查询煤价
    var params = this.getQueryParams();
    this.getProductPriceList(params);

    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          sliderLeft: (res.windowWidth / that.data.tabs.length - sliderWidth) / 2,
          sliderOffset: res.windowWidth / that.data.tabs.length * that.data.activeIndex
        });
      }
    });
  },
  getProductPriceList: function(data) {
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
  showInput: function () {
    this.setData({
      inputShowed: true
    });
  },
  searchPrice: function () {
    
    var params = this.getQueryParams();;
    this.getProductPriceList(params); 
  },
  getQueryParams() {

    var params = {
      productType: this.data.productType,
      factoryType: this.data.factoryType,
      factoryName: this.data.factoryName
    };

    return params;

  },
  clearInput: function () {
    this.setData({
      factoryName: ""
    });

    var params = this.getQueryParams();
    this.getProductPriceList(params); 
  },
  inputTyping: function (e) {
    this.setData({
      factoryName: e.detail.value
    });
  },
  tabClick: function (e) {

    var productType = e.currentTarget.dataset.item;

    this.setData({
      sliderOffset: e.currentTarget.offsetLeft,
      activeIndex: e.currentTarget.id,
      productType: productType
    });

    var params = this.getQueryParams();
    this.getProductPriceList(params); 

  },
  gotoDetail: function (e) {
    wx.navigateTo({
      url: '/pages/logs/logs?data=' + JSON.stringify(e.currentTarget.dataset.item)
    })
  }
})
