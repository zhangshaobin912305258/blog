/*

package com.zhang.blog.security;

import com.zhang.blog.entity.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


*/
/*
 * 类描述：
 * 创建时间：2020/6/1 4:39 下午
 * 创建人：zhang
 *//*



@Component("rbacService")
public class RBacServiceImpl implements RBacService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if(principal == null){
            return false;
        }

        if(authentication instanceof AnonymousAuthenticationToken){
            //check if this uri can be access by anonymous
            //return
        }
        User user = (User) principal;
        Set<String> authorites = authentication.getAuthorities()
                .stream()
                .map(e -> e.getAuthority())
                .collect(Collectors.toSet());
        String uri = request.getRequestURI();
        //check this uri can be access by this role
        List<String> roles = user.getRoles();
        return true;
    }
}

*/
