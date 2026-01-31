package com.sms.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * SceneSwitcher
 *
 * Utility class to switch between JavaFX scenes.
 */
public class SceneSwitcher {

    /**
     * Switch to a new scene.
     *
     * @param stage      The primary stage or any existing stage
     * @param fxmlPath   Path to the FXML file (from resources folder)
     * @param title      Window title
     */
    public static void switchScene(Stage stage, String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("❌ Failed to load scene: " + fxmlPath);
        }
    }

    /**
     * Load FXML and return the controller instance.
     * Useful if you need to pass data to the next controller.
     *
     * @param fxmlPath Path to FXML
     * @return Controller instance of the loaded scene
     */
    public static Object loadController(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(fxmlPath));
            loader.load();
            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("❌ Failed to load controller for: " + fxmlPath);
            return null;
        }
    }
}


