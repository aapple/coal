import axios from '@/libs/api.request'

export const updateCoalPrice = (data) => {
  return axios.request({
    url: 'user/login',
    data,
    method: 'post'
  })
}
