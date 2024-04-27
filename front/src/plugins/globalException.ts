import {stringify} from "querystring";
import {history} from 'umi';
import {RequestConfig} from "@@/plugin-request/request";

import {message} from "antd";

export const customRequestInterceptor = (url: string, options: RequestConfig) => {
  return {
    url: `${url}`,
    options: {...options, interceptors: true},
  };
};

export const customResponseInterceptor = async (response: Response, options: RequestConfig) => {
  const res = await response.clone().json();
  if (res.code === 0) {
    return res.data;
  }

  if (res.code === 40100) {
    history.replace({
      pathname: '/user/login',
      search: stringify({
        redirect: location.pathname,
      }),
    });
  } else {
    message.error(res.description);
  }

  return res.data;
};

