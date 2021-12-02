package com.heavytiger.web;

import com.google.gson.Gson;
import com.heavytiger.entity.User;
import com.heavytiger.service.UserService;
import com.heavytiger.service.impl.UserServiceImpl;
import com.heavytiger.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    UserService userService = new UserServiceImpl();

    /**
     * 注销Servlet
     * @param req 
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean existsUsername = userService.existsUsername(username);
        Map<String, Object> result = new HashMap<>();
        result.put("existsUsername", existsUsername);
        Gson gson = new Gson();
        String json = gson.toJson(result);
        resp.getWriter().write(json);
    }
    
    /**
     * 登录Servlet
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        User signInUser = userService.signInUser(user);

        if(signInUser == null) {
            // 说明登陆失败
            req.setAttribute("msg", "用户名或密码输入错误！");
            req.setAttribute("username", user.getUsername());
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("user", signInUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    /**
     * 注册Servlet
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        // System.out.println(user.toString());
        // 1.检查验证码是否正确，忽略大小写
        String token = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        if(token != null && token.equalsIgnoreCase(req.getParameter("code"))){
            // 检查用户名是否可用
            if(userService.existsUsername(user.getUsername())){
                // 不可用
                req.setAttribute("msg", "用户名["+ user.getUsername() + "]已存在");
                req.setAttribute("username", user.getUsername());
                req.setAttribute("password",user.getPassword_hash());
                req.setAttribute("email", user.getEmail());
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                // 可用
                userService.signUpUser(user);
                req.getSession().setAttribute("user", user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            //说明验证码验证错误，返回注册页面
            req.setAttribute("msg", "验证码错误!");
            req.setAttribute("username", user.getUsername());
            req.setAttribute("password",user.getPassword_hash());
            req.setAttribute("email", user.getEmail());
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }
}
