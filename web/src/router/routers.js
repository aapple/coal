import Main from '@/view/main'
import parentView from '@/components/parent-view'

/**
 * iview-admin中meta除了原生参数外可配置的参数:
 * meta: {
 *  hideInMenu: (false) 设为true后在左侧菜单不会显示该页面选项
 *  notCache: (false) 设为true后页面不会缓存
 *  access: (null) 可访问该页面的权限数组，当前路由设置的权限会影响子路由
 *  icon: (-) 该页面在左侧菜单、面包屑和标签导航处显示的图标，如果是自定义图标，需要在图标名称前加下划线'_'
 * }
 */

export default [
  {
    path: '/login',
    name: 'login',
    meta: {
      title: 'Login - 登录',
      hideInMenu: true
    },
    component: () => import('@/view/login/login.vue')
  },
  {
    path: '/',
    name: 'home',
    redirect: '/home',
    component: Main,
    meta: {
      hideInMenu: true,
      notCache: true
    },
    children: [
      {
        path: 'home',
        name: 'home',
        meta: {
          hideInMenu: true,
          notCache: true
        },
        component: () => import('@/view/coal/coalPriceList.vue')
      }
    ]
  },
  {
    path: '/coal',
    name: 'coal',
    component: Main,
    children: [
      {
        path: 'coalPriceList_page',
        name: 'coalPriceList_page',
        meta: {
          icon: '_qq',
          title: '煤炭价格管理',
          notCache: true
        },
        component: () => import('@/view/coal/coalPriceList.vue')
      },
      {
        path: 'coalPrice_page',
          name: 'coalPrice_page',
        meta: {
        icon: '_qq',
          title: '煤价更新',
          hideInMenu: true,
          notCache: true
        },
        component: () => import('@/view/coal/coalPrice.vue')
      }
    ]
  },
  {
    path: '/coke',
      name: 'coke',
    component: Main,
    children: [
    {
      path: 'cokePriceList_page',
      name: 'cokePriceList_page',
      meta: {
        icon: '_qq',
        title: '兰炭价格管理',
        notCache: true
      },
      component: () => import('@/view/coke/cokePriceList.vue')
    },
      {
        path: 'cokePrice_page',
          name: 'cokePrice_page',
        meta: {
        icon: '_qq',
          title: '兰炭价格更新',
          hideInMenu: true,
          notCache: true
      },
        component: () => import('@/view/coke/cokePrice.vue')
      }
    ]
  },
  {
    path: '/logistics',
      name: 'logistics',
    component: Main,
    children: [
    {
      path: 'logisticsList_page',
      name: 'logisticsList_page',
      meta: {
        icon: '_qq',
        title: '物流单管理',
        notCache: true
      },
      component: () => import('@/view/logistics/logisticsList.vue')
    },
      {
        path: 'logistics_page',
          name: 'logistics_page',
        meta: {
        icon: '_qq',
          title: '物流单更新',
          hideInMenu: true,
          notCache: true
      },
        component: () => import('@/view/logistics/logistics.vue')
      }
    ]
  },
  {
    path: '/factory',
      name: 'factory',
    component: Main,
    children: [
      {
        path: 'factoryList_page',
        name: 'factoryList_page',
        meta: {
          icon: '_qq',
          title: '厂矿管理',
          notCache: true
        },
        component: () => import('@/view/factory/factoryList.vue')
      },
      {
        path: 'factory_page',
          name: 'factory_page',
        meta: {
        icon: '_qq',
          title: '厂矿更新',
          hideInMenu: true,
          notCache: true
      },
        component: () => import('@/view/factory/factory.vue')
      }
    ]
  },
  {
    path: '/user',
      name: 'user',
    component: Main,
    children: [
    {
      path: 'userList_page',
      name: 'userList_page',
      meta: {
        icon: '_qq',
        title: '用户管理',
        notCache: true
      },
      component: () => import('@/view/user/userList.vue')
    },
      {
        path: 'user_page',
          name: 'user_page',
        meta: {
        icon: '_qq',
          title: '用户更新',
          hideInMenu: true,
          notCache: true
      },
        component: () => import('@/view/user/user.vue')
      }
    ]
  },
  {
    path: '/401',
    name: 'error_401',
    meta: {
      hideInMenu: true
    },
    component: () => import('@/view/error-page/401.vue')
  },
  {
    path: '/500',
    name: 'error_500',
    meta: {
      hideInMenu: true
    },
    component: () => import('@/view/error-page/500.vue')
  },
  {
    path: '*',
    name: 'error_404',
    meta: {
      hideInMenu: true
    },
    component: () => import('@/view/error-page/404.vue')
  }
]
