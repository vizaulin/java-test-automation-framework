package project.core.pages.selenium.base;

import org.openqa.selenium.By;

public abstract class BaseComponent extends BaseUI {
    protected final By search = By.xpath("//div[@id='sidebar']//input");
}
