import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import React from 'react';
import {MY_GITHUB} from "@/constants";

const Footer: React.FC = () => {
  const defaultMessage = 'Silenceibtc出品';
  const currentYear = new Date().getFullYear();
  return (
    <DefaultFooter
      style={{
        background: 'none',
      }}
      copyright={`${currentYear} ${defaultMessage}`}
      links={[
        {
          key: 'user-center',
          title: '用户中心',
          href: '#',
          blankTarget: true,
        },
        {
          key: 'github',
          title: <><GithubOutlined />用户中心Github</>,
          href: MY_GITHUB,
          blankTarget: true,
        },
      ]}
    />
  );
};

export default Footer;
