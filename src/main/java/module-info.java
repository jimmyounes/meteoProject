module appmeteo.main {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.web;
    requires gson;
    requires java.net.http;
    opens app.appmeteo;
    opens app.appmeteo.view;
    opens app.appmeteo.controller;
}