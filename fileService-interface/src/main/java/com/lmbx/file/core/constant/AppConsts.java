package com.lmbx.file.core.constant;


public class AppConsts {

    public static final String PROJECT_ID          = "";

    /**
     * 系统里面所有 true 对应 1，数据库表字段类型为 number(1)
     */
    public static final Short  TRUE                = 1;
    /**
     * 系统里面所有 false 对应 0，数据库表字段类型为 number(1)
     */
    public static final Short  FALSE               = 0;

    /**
     * 菜单
     */
    public static final Short  IS_RESOURCE_MENU    = 1;
    /**
     * 有路由配置，但不在导航栏显示的菜单
     */
    public static final String SUB_MENU            = "submenu";
    /**
     * 面包屑菜单导航父节点 ID
     */
    public static final String MENU_ROOT_BPID      = "4dcab7f2ad5f4166996de3ff910ac8c2";

    /**
     * 登录URI
     */
    public static final String QUERY_USER_URI      = "/user";

    /**
     * 来宾
     */
    public static final Short  PERSON_TYPE_1       = 1;

    /**
     * 讲师
     */
    public static final Short  PERSON_TYPE_2       = 2;

    /**
     * 工作人员
     */
    public static final Short  PERSON_TYPE_3       = 3;

    /**
     * 身份证
     */
    public static final Short  ID_NUMBER_TYPE      = 1;

    /**
     * 基本信息
     */
    public static final Short  VERSION_DICT_TYPE_2 = 2;

    /**
     * 登记信息
     */
    public static final Short  VERSION_DICT_TYPE_3 = 3;

    /**
     * 签到信息
     */
    public static final Short  VERSION_DICT_TYPE_4 = 4;

    /**
     * 会议
     */
    public static final Short  EVENT_TYPE_1        = 1;
    /**
     * 课程
     */
    public static final Short  EVENT_TYPE_2        = 2;
    /**
     * 签到
     */
    public static final Short  EVENT_TYPE_3        = 3;

    /**
     * 会议准备期
     */
    public static final Short  CONF_STATUS_1       = 1;

    /**
     * 会议进行中
     */
    public static final Short  CONF_STATUS_2       = 2;

    /**
     * 会议收尾期
     */
    public static final Short  CONF_STATUS_3       = 3;

    /**
     * 会议结束日期
     */
    public static final Short  CONF_STATUS_4       = 4;

    /**
     * 会议附件
     */
    public static final Short  CONF_FILE_TYPE_1    = 1;
    /**
     * 课程附件
     */
    public static final Short  CONF_FILE_TYPE_2    = 2;


    /**
     * 上车签到
     */
    public static final Short  CONF_CHECKIN_TYPE_1    = 1;

    /**
     * 吃饭签到
     */
    public static final Short  CONF_CHECKIN_TYPE_2    = 2;

    /**
     * 会议签到
     */
    public static final Short  CONF_CHECKIN_TYPE_3    = 3;

    /**
     * 课程签到
     */
    public static final Short  CONF_CHECKIN_TYPE_4    = 4;

}
