package fr.thefoxy41.onlinecounter.commons.interfaces;

public interface Plugin {
    void log(String message);

    String getPluginFolder();

    String getServerName();
}
