package bd.edu.seu.movieproject;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class PermissionWindowController  {
    Movie movie = null;

    public void setMovie(Movie movie){
        this.movie = movie;
    }

    public void deleteMovie(ActionEvent actionEvent) {
        System.out.println(movie.getMovieName());
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
