package com.example.attendancev1;

import com.google.gson.annotations.SerializedName;

    public class LoginResult {
        private int user_id;
        private String email;
        private String username;

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        private String role;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
