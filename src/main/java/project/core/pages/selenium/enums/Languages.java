package project.core.pages.selenium.enums;

public enum Languages {
    ENGLISH("English"),
    ITALIANO("Italiano"),
    SUOMI("Suomi");

    private final String name;

    Languages(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
