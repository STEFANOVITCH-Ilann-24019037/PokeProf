package fr.Sae.Test;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class TourAnimator {

    public static void animer(Label label, int numeroTour) {
        label.setText("Tour : " + numeroTour);
        label.setScaleX(0.1);
        label.setScaleY(0.1);
        label.setOpacity(0);
        label.setVisible(true);

        // Animation de zoom
        ScaleTransition scale = new ScaleTransition(Duration.seconds(0.5), label);
        scale.setFromX(0.1);
        scale.setFromY(0.1);
        scale.setToX(1.0);
        scale.setToY(1.0);

        // Fade in
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), label);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        // Fade out après délai
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), label);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setDelay(Duration.seconds(1.5));
        fadeOut.setOnFinished(e -> label.setVisible(false));

        // Lancer animations
        scale.play();
        fadeIn.play();
        fadeOut.play();
    }
}
