package app.domain.entity;

/**
 * @author Anton German
 * @since 08 June 2016
 */
public enum Language {
    EN("English"), RU("Russian");

    private final String humanReadableName;

    Language(String name) {
        this.humanReadableName = name;
    }

    public String getHumanReadableName() {
        return humanReadableName;
    }

    public static Language createFromHumanReadableName(String name) {
        for (Language lang : Language.values()) {
            if (lang.getHumanReadableName().equalsIgnoreCase(name)) {
                return lang;
            }
        }
        throw new IllegalStateException("Cannot find Language enum value for: " + name);
    }
}
