package bd.edu.seu.movieproject;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PermissionWindowController  {
    public boolean isDeleted = false;
    Movie movie = null;

    public void setMovie(Movie movie){
        this.movie = movie;
    }

    public void deleteMovie(ActionEvent actionEvent) {
        try{
            String deleteQuery = "DELETE FROM public.movie_info WHERE movie_id=?";
            Connection connection =
                    DriverManager.getConnection("jdbc:postgresql://localhost:5432/movieProject","postgres","admin");
            System.out.println("Connection established");
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, movie.getMovieID());
            System.out.println(preparedStatement);
            int delete = preparedStatement.executeUpdate();
            if(delete == 1){
                System.out.println( "Movie Deleted : "+ movie.getMovieID());
            }
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void cancelAction(ActionEvent actionEvent) {
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
