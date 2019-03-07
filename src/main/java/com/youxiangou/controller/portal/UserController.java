package com.youxiangou.controller.portal;

import com.youxiangou.common.Const;
import com.youxiangou.common.ResponseCode;
import com.youxiangou.common.ServerResponse;
import com.youxiangou.pojo.User;
import com.youxiangou.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Author ljs
 * @Description TODO
 * @Date 2019/2/16 0:46
 **/
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;
    
    /**
     * Author ljs
     * Description 用户登录
     * Date 2019/2/16 0:50
     **/
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse response = iUserService.login(username, password);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }
    
    /**
     * Author ljs
     * Description 用户注销
     * Date 2019/2/16 17:06
     **/
    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccessMessage("退出成功!");
    }
    
    /**
     * Author ljs
     * Description 用户注册
     * Date 2019/2/16 21:40
     **/
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user){
        return iUserService.register(user);
    }
    
    /**
     * Author ljs
     * Description 校验username和email是存在
     * Date 2019/2/16 21:57
     **/
    @RequestMapping(value = "check_vaild.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkVaild(String str, String type){
        return iUserService.checkValid(str, type);
    }
    
    /**
     * Author ljs
     * Description 获取当前用户信息
     * Date 2019/2/17 0:40
     **/
    @RequestMapping(value = "get_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session){
            User user = (User) session.getAttribute(Const.CURRENT_USER);
            if(user != null){
                return ServerResponse.createBySuccess(user);
            }
        return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
    }

    /**
     * Author ljs
     * Description 根据用户名查找问题
     * Date 2019/2/17 0:46
     **/
    @RequestMapping(value = "forget_get_question.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username){
        return iUserService.selectQuestion(username);
    }

    /**
     * Author ljs
     * Description 校验忘记密码问题的答案
     * Date 2019/2/17 1:24
     **/
    @RequestMapping(value = "forget_check_answer.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username,String question,String answer){
        return iUserService.checkAnswer(username,question,answer);
    }
    
    /**
     * Author ljs
     * Description 未登录重置密码
     * Date 2019/2/17 1:27
     **/
    @RequestMapping(value = "forget_reset_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(String username,String passwordNew,String forgetToken){
        return iUserService.forgetResetPassword(username,passwordNew,forgetToken);
    }
    
    /**
     * Author ljs
     * Description 登录状态下重置密码
     * Date 2019/2/17 1:53
     **/
    @RequestMapping(value = "reset_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session,String passwordOld,String passwordNew){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iUserService.resetPassword(passwordOld,passwordNew,user);
    }
    
    /**
     * Author ljs
     * Description 个人中心更新用户信息
     * Date 2019/2/17 2:13
     **/
    @RequestMapping(value = "update_information.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> update_information(HttpSession session,User user){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        //新传进来的user没有userid
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        ServerResponse<User> response = iUserService.updateInformation(user);
        if(response.isSuccess()){
            response.getData().setUsername(currentUser.getUsername());
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }
    
    /**
     * Author ljs
     * Description 个人中心获取用户信息并强制登录
     * Date 2019/2/17 10:43
     **/
    @RequestMapping(value = "get_information.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> get_information(HttpSession session){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录,需要强制登录status=10");
        }
        return iUserService.getInformation(currentUser.getId());
    }


}
