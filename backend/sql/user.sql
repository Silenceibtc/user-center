Data Source: user-center@localhost Schema: user-center Table: user  -- 用户表
-- auto-generated definition
create table user
(
    id           bigint auto_increment comment 'id'
        primary key,
    username     varchar(256)                       null comment '用户名',
    gender       tinyint                            null comment '性别 0 - 女 1 - 男',
    avatarUrl    varchar(1024)                      null comment '头像url
',
    userAccount  varchar(256)                       null comment '账号',
    userPassword varchar(512)                       not null comment '用户密码',
    phone        varchar(128)                       null comment '电话号码',
    email        varchar(512)                       null comment '邮箱',
    userStatus   int      default 0                 not null comment '用户状态 0 - 正常',
    createTime   datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint  default 0                 not null comment '是否删除 0 - 未删除',
    identity     int      default 1                 not null comment '用户身份 0 - 管理员 1 - 普通用户'
)
    comment '用户表';

alter table user add column tags varchar(1024) null comment '标签列表';