package cn.edu.dlmu.back.service;

import cn.edu.dlmu.back.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author Silenceibtc
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-03-11 21:36:43
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     * @param originUser 初始用户
     * @return 脱敏后的用户
     */
    User getSafetyUser(User originUser);

    /**
     * 用户注销
     *
     * @param request
     */
    int userLogout(HttpServletRequest request);

    /**
     * 根据标签搜索用户
     *
     * @param tagNameList 标签列表
     * @return 符合条件的脱敏后的所有用户
     */
    List<User> searchUsersByTags(List<String> tagNameList);
}
