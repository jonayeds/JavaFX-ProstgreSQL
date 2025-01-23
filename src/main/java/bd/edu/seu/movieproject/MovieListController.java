package bd.edu.seu.movieproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MovieListController implements Initializable {
    @FXML
    private TableView<Movie> movie_tableview;

    @FXML
    private TableColumn<Movie, Integer> movie_id_col;
    @FXML
    private TableColumn<Movie, String> movie_name_col;
    @FXML
    private TableColumn<Movie, String> movie_category_col;
    @FXML
    private TableColumn<Movie, LocalDate> movie_release_date_col;
    @FXML
    private TableColumn<Movie, String> movie_country_col;
    @FXML
    private TableColumn action_col;
    @FXML
    private AnchorPane list_panel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Mapping between tableview and model
        mappingBetweenTableAndModel();
        // Database connection
        Connection dbConnection = getDBConnection();
        // Data get from database
        ResultSet data = getDataFromDatabase(dbConnection);
        // Data transform to model
        List<Movie> mData = dataTransformToModel(data);
        // Data show tableview
        dataShowInTable(mData);
    }



    private void dataShowInTable(List<Movie> mData) {
        // button inside the table cel
        Callback<TableColumn<Movie, String>, TableCell<Movie, String>> cellFactory = (parms) -> {
            final TableCell<Movie, String> cell = new TableCell<>(){
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    }else{
                        final HBox hBox = new HBox();
                        hBox.setSpacing(2);
                        final Button edit = new Button("Edit");
                        final Button delete = new Button("Delete");
                        edit.setOnAction(event -> {
                            Movie m = getTableView().getItems().get(getIndex());
                            System.out.println("Edit of: " + m.getMovieID());
                            // apply your edit logic
                            new SceneSwitch(list_panel, "update-movie_view.fxml", m);




                        });
                        delete.setOnAction(event -> {
                            Movie m = getTableView().getItems().get(getIndex());
                            System.out.println("Delete of: " + m.getMovieID());
                            // apply your delete logic
                        });
                        hBox.getChildren().addAll(edit,delete);
                        setGraphic(hBox);
                        setText(null);
                    }

                }
            };
            return cell;
        };
        action_col.setCellFactory(cellFactory);
        movie_tableview.getItems().addAll(mData);
    }

    private List<Movie> dataTransformToModel(ResultSet data) {
        List<Movie> movieData = new ArrayList<>();
        try {
            while (data.next()){
                int id = data.getInt("movie_id");
                String name = data.getString("movie_name");
                String category = data.getString("movie_category");
                LocalDate rDate = LocalDate.parse(data.getDate("movie_release_date").toString());
                String country = data.getString("movie_country");
                LocalDateTime createAt = data.getTimestamp("create_at").toLocalDateTime();
                LocalDateTime updateAt = null;
                if (data.getTimestamp("update_at")!=null){
                    updateAt = data.getTimestamp("update_at").toLocalDateTime();
                }

                Movie m = new Movie(id, name, category, rDate, country, createAt, updateAt);
                movieData.add(m);
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return movieData;
    }

    private ResultSet getDataFromDatabase(Connection dbConnection) {
        // insert, update, delete ---> executeUpdate
        // select                 ---> executeQuery
        ResultSet resultSet = null;
        PreparedStatement selectStatement = null;
        try {
            String sql = "SELECT * FROM public.movie_info";
            selectStatement =
                    dbConnection.prepareStatement(sql);
            resultSet = selectStatement.executeQuery();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return resultSet;
    }

    private Connection getDBConnection() {
        Connection connection = null;
        try {
            connection =
                    DriverManager.getConnection("jdbc:postgresql://localhost:5432/movieProject","postgres","admin");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.out.println("Connection Failed!!!!");
        }
        return connection;
    }

    private void mappingBetweenTableAndModel() {
        movie_id_col.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("movieID"));
        movie_name_col.setCellValueFactory(new PropertyValueFactory<Movie, String>("movieName"));
        movie_category_col.setCellValueFactory(new PropertyValueFactory<Movie, String>("movieCategory"));
        movie_release_date_col.setCellValueFactory(new PropertyValueFactory<Movie, LocalDate>("movieReleaseDate"));
        movie_country_col.setCellValueFactory(new PropertyValueFactory<Movie, String>("country"));
    }

}
