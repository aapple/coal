
var sliderWidth = 60;
var subSliderWidth = 60;

Page({
  data: {
    inputShowed: false,
    inputVal: "",
    prices: [],
    tabs: [],
    subTabs: [],
    productTyps: [],
    activeIndex: 0,
    subActiveIndex: 0,
    sliderOffset: 0,
    sliderLeft: 0,
    subSliderOffset: 0,
    subSliderLeft: 0,
    productType: 1,
    factoryType: "1",
    priceOwnerType: "2"
  },
  onShow: function () {
    var that = this;

    // 查询煤种
    this.getProductTypeList();

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
      url: 'https://coalapp.smmeitan.cn/app/product/getProductPriceList',
      data: data,
      method: "POST",
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {

        that.setData({
          prices: res.data
        });
      }
    })

  },
  getProductTypeList: function () {
    var that = this;
    wx.request({
      url: 'https://coalapp.smmeitan.cn/app/product/getProductTypeList',
      data: {
        factoryType: "1",
        name: "tab"
      },
      method: "POST",
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {

        that.setData({
          productTyps: res.data,
          tabs: res.data 
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
    
    var params = this.getQueryParams();
    params.factory = { name: this.data.inputVal };
    this.getProductPriceList(params); 
  },
  getQueryParams() {

    var params = {
      productType: this.data.productType != -1 ? { id: this.data.productType } : null,
      factory: { factoryType: this.factoryType }
    };

    if (this.data.factoryType == 1 + "") {
      params.priceOwnerType = this.data.priceOwnerType;
    }

    return params;

  },
  clearInput: function () {
    this.setData({
      inputVal: ""
    });

    var params = this.getQueryParams();
    this.getProductPriceList(params); 
  },
  inputTyping: function (e) {
    this.setData({
      inputVal: e.detail.value
    });
  },
  tabClick: function (e) {

    var productType = "";
    var subTabs = [];
    var item = e.currentTarget.dataset.item;
    if (item.subList != undefined && item.subList.length > 0){
      productType = item.subList[0].id;
      subTabs = item.subList;
    } else {
      productType = item.id;
    }

    this.setData({
      sliderOffset: e.currentTarget.offsetLeft,
      activeIndex: e.currentTarget.id,
      subSliderOffset: 0,
      subActiveIndex: e.currentTarget.id,
      subActiveIndex : 0,
      productType: productType,
      subTabs: subTabs
    });

    var that = this;
    if (this.data.subTabs.length == 5) {
      subSliderWidth = 70;
    } else if (this.data.subTabs.length == 4) {
      subSliderWidth = 80;
    } else if (this.data.subTabs.length == 5) {
      subSliderWidth = 100
    }

    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          subSliderLeft: (res.windowWidth / that.data.subTabs.length - subSliderWidth) / 2,
        });
      }
    });

    var params = this.getQueryParams();
    this.getProductPriceList(params); 

  },
  subTabClick: function (e) {

    var that = this;
    if (this.data.subTabs.length == 5){
      subSliderWidth = 70;
    } else if (this.data.subTabs.length == 4){
      subSliderWidth = 80;
    } else if (this.data.subTabs.length == 5) {
      subSliderWidth = 100
    }

    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          subSliderLeft: (res.windowWidth / that.data.subTabs.length - subSliderWidth) / 2,
          subSliderOffset: res.windowWidth / that.data.subTabs.length * e.currentTarget.id
        });
      }
    });

    this.setData({
      subSliderOffset: e.currentTarget.offsetLeft,
      subActiveIndex: e.currentTarget.id,
      productType: e.currentTarget.dataset.item.id
    });

    var params = this.getQueryParams();
    this.getProductPriceList(params);

  },
  gotoDetail: function (e) {
    wx.navigateTo({
      url: '/pages/logs/logs?data=' + JSON.stringify(e.currentTarget.dataset.item)
    })
  },
  changeOwner: function (e) {
    console.log(1)
    this.setData({
      priceOwnerType: e.currentTarget.dataset.item
    });

    var params = this.getQueryParams();
    this.getProductPriceList(params);
  }
})
