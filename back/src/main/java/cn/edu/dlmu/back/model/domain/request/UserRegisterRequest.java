package cn.edu.dlmu.back.model.domain.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 1684212231155798625L;

    /**
     * 用户账户
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户校验密码
     */
    private String checkPassword;

}
