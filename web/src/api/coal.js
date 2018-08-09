import axios from '@/libs/api.request'

export const updateCoalPrice = (data) => {
  return axios.request({
    url: 'coalPrice/update',
    data,
    method: 'post'
  })
}

export const queryCoalPriceList = (data) => {
  return axios.request({
    url: 'coalPrice/query',
    data,
    method: 'post'
  })
}


export const updateFactory = (data) => {
  return axios.request({
    url: 'factory/update',
    data,
    method: 'post'
  })
}

export const queryFactoryList = (data) => {
  return axios.request({
    url: 'factory/query',
    data,
    method: 'post'
  })
}
