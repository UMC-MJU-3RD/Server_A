package com.example.umc_tommy.utils;

import com.example.umc_tommy.model.entity.user.User;
import com.example.umc_tommy.service.user.UserDetails;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {

    public static long getId() {
        return getEntity().getId();
    }

    public static User getEntity() {
        return getEntity(true);
    }

    public static User getEntity(boolean shouldHidePassword) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        User User = getDetails().getUser();
        if (shouldHidePassword) {
            User.setPassword(null);
        }
        return User;
    }

    public static UserDetails getDetails() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return (UserDetails) securityContext.getAuthentication().getPrincipal();
    }
}
