package app.appmeteo.controller;

import app.appmeteo.model.DataManager;
import app.appmeteo.view.ViewFactory;

public abstract class BaseController {
    protected DataManager dataManager;
    protected ViewFactory viewFactory;
    private String fxmlName;

    public BaseController(DataManager dataManager, ViewFactory viewFactory, String fxmlName) {
        this.dataManager = dataManager;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    public String getFxmlName() { return fxmlName; }
}
