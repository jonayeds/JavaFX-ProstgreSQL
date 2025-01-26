package bd.edu.seu.movieproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class UpdateMovieController implements Initializable {
    public TextField tf_movie_name;
    public DatePicker date_movie_release;
    public ComboBox cb_movie_country;
    public ComboBox cb_movie_category;
    @FXML
    public AnchorPane update_panel;

    private int movieId ;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        List<String> movieCategoryList = new ArrayList<>();
        movieCategoryList.add("Select One");
        movieCategoryList.add("Adventure");
        movieCategoryList.add("Crime");
        movieCategoryList.add("SiFi");
        movieCategoryList.add("Romantic");

        List<String> countryList = new ArrayList<>();
        countryList.add("Select One");
        countryList.add("Bangladesh");
        countryList.add("UK");
        countryList.add("USA");

        cb_movie_category.getItems().addAll(movieCategoryList);
        cb_movie_country.getItems().addAll(countryList);
        cb_movie_country.getSelectionModel().selectFirst();
        cb_movie_category.getSelectionModel().selectFirst();
    }


    public void setValues(Movie movie){
        tf_movie_name.setText(movie.getMovieName());
        cb_movie_country.setValue(movie.getCountry());
        cb_movie_category.setValue(movie.getMovieCategory());
        date_movie_release.setValue(movie.getMovieReleaseDate());
        this.movieId= movie.getMovieID();


    }




    public void updateMovieInformation(ActionEvent actionEvent) {

        Date newReleaseDate = Date.valueOf(date_movie_release.getValue());

        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:postgresql://localhost:5432/movieProject","postgres","admin");
            System.out.println("Connection established!!!!");
            String updateQuery = "UPDATE public.movie_info SET movie_name=?, movie_category=?, movie_country=?, movie_release_date = ? WHERE movie_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, tf_movie_name.getText());
            preparedStatement.setString(2, cb_movie_category.getValue().toString());
            preparedStatement.setString(3, cb_movie_country.getValue().toString());
            preparedStatement.setDate(4, newReleaseDate);
            preparedStatement.setInt(5, movieId);
            int update = preparedStatement.executeUpdate();
            if(update == 1){
                System.out.println("Successfully updated");
                new SceneSwitch(update_panel, "movie-list_view.fxml");

            }else{
                System.out.println("Failed to updated");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
