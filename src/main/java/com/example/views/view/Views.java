package com.example.views.view;

import org.springframework.stereotype.Component;

@Component
public class Views {
    public static class UserSummary {}
    public static class UserDetails extends UserSummary {}
}
