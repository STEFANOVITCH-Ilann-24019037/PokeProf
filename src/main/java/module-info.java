
module fr.IlannStefanovitch.PokeProf {
        requires javafx.controls;
        requires javafx.fxml;
    requires java.desktop;

    opens fr.IlannStefanovitch.PokeProf to javafx.fxml;
        exports fr.IlannStefanovitch.PokeProf;

        opens fr.Sae.Test to javafx.fxml;
        exports fr.Sae.Test;
        }
