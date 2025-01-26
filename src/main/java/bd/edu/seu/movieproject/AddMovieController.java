package bd.edu.seu.movieproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import java.sql.*;

public class AddMovieController implements Initializable {

    @FXML
    private TextField tf_movie_name;
    @FXML
    private ComboBox<String> cb_movie_category;
    @FXML
    private DatePicker date_movie_release;
    @FXML
    private ComboBox<String> cb_movie_country;
    @FXML
    public AnchorPane add_panel;

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
    public void saveMovieInformation(ActionEvent actionEvent) {
        /**
         * 1. Connection Setup
         * 2. Data Collection
         * 3. Execute Scripts -> in this case, we need implement INSERT script
         */

        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:postgresql://localhost:5432/movieProject","postgres","admin");
            System.out.println("Connection established!!!!");

            // data collection
            String movieName = tf_movie_name.getText();
            String movieCategory = cb_movie_category.getValue();
            Date movieReleaseDate = Date.valueOf(date_movie_release.getValue());
            String movieCountry = cb_movie_country.getValue();

            // data insert

            String insertScript = "INSERT INTO public.movie_info(movie_name, movie_category, movie_release_date, movie_country) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertScript);
            preparedStatement.setString(1, movieName);
            preparedStatement.setString(2, movieCategory);
            preparedStatement.setDate(3, movieReleaseDate);
            preparedStatement.setString(4, movieCountry);
            preparedStatement.executeUpdate();
            System.out.println("Movie Added Successfully!!!");
            new SceneSwitch(add_panel, "movie-list_view.fxml");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println("Connection problem!!!");
        }

    }
}
