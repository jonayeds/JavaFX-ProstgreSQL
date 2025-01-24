package bd.edu.seu.movieproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneSwitch {
    public SceneSwitch(AnchorPane page, String fxmlName){
        try {
            AnchorPane loader =
                    FXMLLoader.load(Objects.requireNonNull(MovieApplication.class.getResource(fxmlName)));
            page.getChildren().removeAll();
            page.getChildren().setAll(loader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public SceneSwitch(AnchorPane page, String fxmlName, Movie movie){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlName));
            Parent root = loader.load();
            UpdateMovieController controller = loader.getController();
            controller.setValues(movie);
            page.getChildren().removeAll();
            page.getChildren().setAll(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public SceneSwitch(Movie movie){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("popup-permission.fxml"));
            Parent root = loader.load();
            Stage popupStage = new Stage();
            PermissionWindowController controller = loader.getController();
            controller.setMovie(movie);
            Scene scene = new Scene(root);
            popupStage.setScene(scene);

            popupStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
