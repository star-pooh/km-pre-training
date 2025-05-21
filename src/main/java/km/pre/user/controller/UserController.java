package km.pre.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import km.pre.user.dto.request.LoginRequestDto;
import km.pre.user.entity.User;
import km.pre.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/home")
    public String home() {
        return "user/home";
    }

    @GetMapping("/welcome")
    public String welcome(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("user") == null) {
            return "redirect:/users/home";
        }
        
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        return "user/welcome";
    }

    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto requestDto, HttpServletRequest request) {
        User user = userService.login(requestDto);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        return "/users/welcome";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return "redirect:/users/home";
    }
}
