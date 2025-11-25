package project.helpers;

import io.qameta.allure.Step;
import project.managers.Pages;

public class TemplatesHelper {

    @Step
    public boolean searchTemplateAndCheckKeywordInIt(int templateNumber, String word) {
        return Pages
                .templatesPage()
                .openPage()
                .search(word)
                .openTemplate(templateNumber)
                .isKeyword(word);
    }
}
