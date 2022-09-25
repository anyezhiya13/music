import Axios from 'axios'
import router from '../router'
Axios.defaults.timeout = 5000 // 超时时间是五秒
Axios.defaults.withCredentials = true // 允许跨域
// Contnt-Type 响应头
Axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
// 基础url
Axios.defaults.baseURL = 'http://81.68.118.179:8945'

Axios.interceptors.response.use(
  response => {
    // 如果返回的状态码为200，说明接口请求成功，可以正常拿到数据
    // 否则的话抛出错误
    if (response.status === 200) {
      return Promise.resolve(response)
    } else {
      return Promise.reject(response)
    }
  },
  // 服务器状态码不是2开头的的情况
  error => {
    if (error.response.status) {
      switch (error.response.status) {
        // 401: 未登录
        case 401:
          router.replace({
            path: '/',
            query: {
              redirect: router.currentRoute.fullPath
            }
          })
          break
        case 404: // 没找到
          break
      }
      return Promise.reject(error.response)
    }
  }
)
/**
 * get
 * @param {*} url
 * @param {*} params
 * @returns null
 */
export function get (url, params = {}) {
  return new Promise((resolve, reject) => {
    Axios.get(url, { params: params })
      .then(response => {
        resolve(response.data)
      })
      .catch(err => {
        reject(err)
      })
  })
}
export function post (url, data = {}) {
  return new Promise((resolve, reject) => {
    Axios.post(url, data)
      .then(response => {
        resolve(response.data)
      })
      .catch(err => {
        reject(err)
      })
  })
}
