package com.assignment.remote.utils;

public final class UrlConstraint {

    public static final String ROOT = "/api";

    private UrlConstraint() {
    }

    public static class FamilyApiManagement {
        public static final String create = "/create";
        public static final String update = "/update/{id}";
        public static final String delete = "/delete/{id}";
        public static final String getAll = "/getAll";
    }
}
