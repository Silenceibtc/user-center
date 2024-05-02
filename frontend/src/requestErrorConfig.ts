import type {RequestOptions} from '@@/plugin-request/request';
import {history, RequestConfig} from '@umijs/max';
import {message} from 'antd';

/**
 * @name 错误处理
 * pro 自带的错误处理， 可以在这里做自己的改动
 * @doc https://umijs.org/docs/max/request#配置
 */
export const errorConfig: RequestConfig = {
  baseURL: process.env.NODE_ENV === 'production' ? 'http://user-center-backend.sibtc.top/api' : '/api',
  timeout: 1000,
  // 错误处理： umi@3 的错误处理方案。
  errorConfig: {
    // 错误抛出
    errorThrower: (res) => {
      const {code, message: msg, description} =
        res as unknown as API.BaseResponse<any>;
      if (!(code === 0)) {
        const error: any = new Error(msg);
        error.name = 'customError';
        error.info = {code, msg, description};
        throw error; // 抛出自制的错误
      }
    },
    // 错误接收及处理
    errorHandler: (error: any, opts: any) => {
      if (opts?.skipErrorHandler) throw error;
      // 自定义的 errorThrower 抛出的错误。
      if (error.name === 'customError') {
        const errorInfo: API.BaseResponse<any> | undefined = error.info;
        if (errorInfo) {
          message.error(errorInfo.message);
        }
      } else if (error.response) {
        // Axios 的错误
        // 请求成功发出且服务器也响应了状态码，但状态代码超出了 2xx 的范围
        message.error(`Response status:${error.response.status}`);
      } else if (error.request) {
        // 请求已经成功发起，但没有收到响应
        // \`error.request\` 在浏览器中是 XMLHttpRequest 的实例，
        // 而在node.js中是 http.ClientRequest 的实例
        message.error('None response! Please retry.');
      } else {
        // 发送请求时出了点问题
        message.error('Request error, please retry.');
      }
    },
  },

  // 请求拦截器
  requestInterceptors: [
    (config: RequestOptions) => {
      // 拦截请求配置，进行个性化处理。
      const url = config?.url;
      return {...config, url};
    },
  ],

  // 响应拦截器
  responseInterceptors: [
    (response) => {
      // 拦截响应数据，进行个性化处理
      const {data} = response as unknown as API.BaseResponse<any>;
      const res = data;

      if (res.code === 0) {
        return res;
      }

      if (res.code === 40100) {
        history.replace('/user/login');
      } else {
        message.error(res.description);
      }

      return res;
    },
  ],
};
