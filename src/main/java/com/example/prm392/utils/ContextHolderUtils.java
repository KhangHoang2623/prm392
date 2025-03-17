package com.example.prm392.utils;

import com.example.prm392.Entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class ContextHolderUtils {
    public static User getContext() {
        if (SecurityContextHolder.getContext() == null) {
            return null;
        }
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
