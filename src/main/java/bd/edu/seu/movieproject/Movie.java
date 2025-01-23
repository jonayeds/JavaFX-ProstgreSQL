package bd.edu.seu.movieproject;

import java.time.LocalDate;
import java.time.LocalDateTime;

// it's a model/Entity, who represent the database table

// database(data) ---> java(LocalDate)
// database(Timestamp) ---> java(LocalDateTime)
public class Movie {
    private int movieID;
    private String movieName;
    private String movieCategory;
    private LocalDate movieReleaseDate;
    private String country;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Movie(int movieID, String movieName, String movieCategory, LocalDate movieReleaseDate, String country, LocalDateTime createAt, LocalDateTime updateAt) {
        this.movieID = movieID;
        this.movieName = movieName;
        this.movieCategory = movieCategory;
        this.movieReleaseDate = movieReleaseDate;
        this.country = country;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Movie(int movieID, String movieName, String movieCategory, LocalDate movieReleaseDate, String country) {
        this.movieID = movieID;
        this.movieName = movieName;
        this.movieCategory = movieCategory;
        this.movieReleaseDate = movieReleaseDate;
        this.country = country;
    }

    public Movie(String movieName, String movieCategory, LocalDate movieReleaseDate, String country) {
        this.movieName = movieName;
        this.movieCategory = movieCategory;
        this.movieReleaseDate = movieReleaseDate;
        this.country = country;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieCategory() {
        return movieCategory;
    }

    public void setMovieCategory(String movieCategory) {
        this.movieCategory = movieCategory;
    }

    public LocalDate getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(LocalDate movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
