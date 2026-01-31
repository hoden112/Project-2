package com.sms.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * ConfirmationDialogUtil
 *
 * Utility class for showing confirmation dialogs in JavaFX.
 */
public class ConfirmationDialogUtil {

    /**
     * Show a confirmation dialog with Yes/No options.
     *
     * @param title   The title of the dialog
     * @param message The message to ask the user
     * @return true if user clicks YES (OK), false if NO or closed
     */
    public static boolean showConfirmation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();

        return result.isPresent() && result.get() == ButtonType.OK;
    }
}

