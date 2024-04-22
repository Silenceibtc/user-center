package cn.edu.dlmu.back.controller;

import cn.edu.dlmu.back.common.BaseResponse;
import cn.edu.dlmu.back.common.ResultUtils;
import cn.edu.dlmu.back.constant.UserConstant;
import cn.edu.dlmu.back.model.domain.User;
import cn.edu.dlmu.back.model.domain.request.UserLoginRequest;
import cn.edu.dlmu.back.model.domain.request.UserRegisterRequest;
import cn.edu.dlmu.back.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户接口
 *
 * @author Silenceibtc
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {

        if (userRegisterRequest == null) {
            return null;
        }

        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();

        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }

        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {

        log.info("user login, params:{}", userLoginRequest);

        if (userLoginRequest == null) {
            return null;
        }

        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();

        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }

        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        int result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request) {
        //鉴权
        if (isAdmin(request)) {
            return null;
        }
        //查询用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> userList = userService.list(queryWrapper);
        //用户信息脱敏
        List<User> safetyUserList = userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
        return ResultUtils.success(safetyUserList);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> searchUsers(Long id, HttpServletRequest request) {
        if (isAdmin(request)) {
            return null;
        }
        if (id <= 0) {
            return null;
        }
        boolean result = userService.removeById(id);
        return ResultUtils.success(result);
    }

    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        User user = (User) userObj;
        if (user == null) {
            return null;
        }
        Long userId = user.getId();
        //获取最新的用户信息
        //todo 校验用户是否合法
        User currentUser = userService.getById(userId);
        User safetyUser = userService.getSafetyUser(currentUser);
        return ResultUtils.success(safetyUser);
    }

    /**
     * 判断是否为管理员
     *
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        User user = (User) userObj;
        return user == null || user.getIdentity() != UserConstant.ADMIN;
    }
}
