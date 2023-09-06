package com.sumit.movieticketbooking.controller;

import java.util.HashMap;
import java.util.List;

/*import javax.validation.Valid;*/

import com.sumit.movieticketbooking.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sumit.movieticketbooking.DAO.BookingsDAO;
import com.sumit.movieticketbooking.DAO.CityDAO;
import com.sumit.movieticketbooking.DAO.MovieDAO;
import com.sumit.movieticketbooking.DAO.ShowDAO;
import com.sumit.movieticketbooking.DAO.TheatreDAO;

/*
@Author : Sumit Yadav
@Description : Controller for admin users to onboard their theatres over this movie booking platform
*/

@RestController
@RequestMapping("/api/v1/admin/")
public class TheatrePartnersController {
	
	@Autowired
	CityDAO theCityDAO;
	
	@Autowired
	MovieDAO theMovieDAO;
	
	@Autowired
	TheatreDAO theTheatreDAO;
	
	@Autowired
	ShowDAO theShowDAO;
	
	@Autowired
	BookingsDAO theBookingsDAO;
	
	//	@Autowired
	// 	TheatreMovieDAO theTheatreMovieDAO;
	
    //	For debugging purpose
	@Operation(summary = "Print hello world for debugging purpose")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "response returned",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = String.class))}),
	})
	@GetMapping("/test")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String returnString() {
		return "hello world";
	}
	
	
    //	Add city to DB
	@Operation(summary = "Print hello world for debugging purpose")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "response returned",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = String.class))}),
	})
	@PostMapping("/city")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public City createCity( @RequestBody City c) {
		return theCityDAO.save(c);
	}
	
	
	//	Add Movie to the database table
	@Operation(summary = "Add movie to the database table")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Movie added to the database table",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = Movie.class)) }),
	})
	@PostMapping("/movie")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Movie createMovie( @RequestBody Movie m) {
		return theMovieDAO.save(m);
	}
	
	//	Add Theatre to the database table
	@Operation(summary = "Add Theatre to the database table")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Theatre added to the database table",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = Theatre.class)) }),
	})
	@PostMapping("/{cityId}/theatre")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Theatre createTheatre(@PathVariable(value="cityId") long cityId,  @RequestBody HashMap<String, String> requestData) {
     //		return theTheatreDAO.save(t);
		Theatre theTheatre = new Theatre();
		theTheatre.setT_name(requestData.get("name"));
		theTheatre.setT_area(requestData.get("area"));
		City theCity = theCityDAO.findOne(cityId);
		theTheatre.setCity(theCity);
		return theTheatreDAO.save(theTheatre);
	}
	
    //	adding show for a theatre and movie
	@Operation(summary = "Add Show to the theatre and movie")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Show added to the theatre and movie",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = Show.class)) }),
	})
	@PostMapping("/{TheatreID}/{movieId}/show")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Show addingShow(@PathVariable(value = "TheatreID") long TheatreID, @PathVariable(value = "movieId") long movieId,  @RequestBody HashMap<String, String> requestData) {
		Theatre theTheatre = theTheatreDAO.findOne(TheatreID);
		Movie theMovie = theMovieDAO.findOne(movieId);
		Show theShow = new Show();
		Show theResponseShow = new Show();
		Booking theBookings = new Booking();
		theShow.setShow_time(requestData.get("time"));
		theShow.setTheMovie(theMovie);
		theShow.setTheTheatre(theTheatre);
		theResponseShow = theShowDAO.save(theShow);
		theBookings.setShow_id(theResponseShow.getShow_Id());
		theBookingsDAO.bookTheSeat(theBookings);
		return theResponseShow;
	}
	
    //	get all Cities
	@Operation(summary = "Get List of Cities")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retrieved List of Cities",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = List.class)) }),
	})
	@GetMapping("/city")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<City> getCity(){
		return theCityDAO.getCity(); 
	}
	
    //	Get all the Movies
	@Operation(summary = "Get List of Movies")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retrieved List of Movies",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = List.class)) }),
	})
	@GetMapping("/movie")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Movie> getMovie(){
		return theMovieDAO.getMovie();
	}
	
    //	Get all the Theatres
	@Operation(summary = "Get List of Theatres")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retrieved List of Theatres",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = List.class)) }),
	})
	@GetMapping("/theatre")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Theatre> getTheatre(){
		return theTheatreDAO.getTheatre();
	}

    //	get City by city ID
	@Operation(summary = "Get City by cityID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retrieved City by cityID",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = City.class)) }),
	})
	@GetMapping("/city/{ID}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<City> getOneCity(@PathVariable(value="ID") long cityID){
		City theCity = theCityDAO.findOne(cityID);
		if(theCity == null) {
			 return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(theCity);
	
	}
	
    //	Get movie by movie ID
	@Operation(summary = "Get Movie by movieID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Get Movie by movieID",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = Movie.class)) }),
	})
	@GetMapping("/movie/{ID}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Movie> getOneMovie(@PathVariable(value="ID") long movieID){
		Movie theMovie = theMovieDAO.findOne(movieID);
		if(theMovie == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(theMovie);
	}
	
    //	Get Theatre By theatreID
	@Operation(summary = "Get Theatre by theatreID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retrieved Theatre by theatreID",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = Theatre.class)) }),
	})
	@GetMapping("/theatre/{ID}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Theatre> getOneTheatre(@PathVariable(value="ID") long theatreID){
		Theatre theTheatre = theTheatreDAO.findOne(theatreID);
		if(theTheatre == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(theTheatre);
	}
	
	

	
	
    //	Update the City

	@Operation(summary = "Update the City by cityID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Updated the City",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = City.class)) }),
	})
	@PutMapping("/city/{ID}")
	public ResponseEntity<City> updateCity(@PathVariable(value="ID") long ID,  @RequestBody City c){
		
		City theCity = theCityDAO.findOne(ID);
		
		if(theCity == null) {
			return ResponseEntity.notFound().build();
			
		}
		theCity.setC_name(c.getC_name());
		theCity.setC_pincode(c.getC_pincode());
		theCity.setC_state(c.getC_state());
		
		City updatedCity = theCityDAO.save(theCity);
		return ResponseEntity.ok().body(updatedCity);
		
	}
	
    //	update Movie
	@Operation(summary = "Update the Movie by movieID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Updated the Movie",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = Movie.class)) }),
	})
	@PutMapping("/movie/{ID}")
	public ResponseEntity<Movie> updateMovie(@PathVariable(value="ID") long ID, @RequestBody Movie m){
		Movie theMovie = theMovieDAO.findOne(ID);
		if(theMovie == null) {
			return ResponseEntity.notFound().build();
		}
		theMovie.setM_name(m.getM_name());
		theMovie.setM_director(m.getM_director());
		theMovie.setM_description(m.getM_description());
		
		Movie updatedMovie = theMovieDAO.save(theMovie);
		return ResponseEntity.ok().body(updatedMovie);
	}
	
    //	update theatre
	@Operation(summary = "Update the theatre by theatreID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Updated the theatre",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = Theatre.class)) }),
	})
	@PutMapping("/theatre/{ID}")
	public ResponseEntity<Theatre> updatetheatre(@PathVariable(value="ID") long ID,  @RequestBody Theatre t){
		
		Theatre theTheatre = theTheatreDAO.findOne(ID);
		if(theTheatre == null) {
			return ResponseEntity.notFound().build();
		}
		theTheatre.setT_name(t.getT_name());
		theTheatre.setT_area(t.getT_area());
		
		Theatre updatedTheatre = theTheatreDAO.save(theTheatre);
		return ResponseEntity.ok().body(updatedTheatre);
		
	}

	
    //	Delete City Data
	@Operation(summary = "Delete the City by cityID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Deleted the City",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = City.class)) }),
	})
	@DeleteMapping("/city/{ID}")
	public ResponseEntity<City> deleteCity(@PathVariable(value="ID") long ID){
		
		City theCity = theCityDAO.findOne(ID);
		if(theCity == null) {
			return ResponseEntity.notFound().build();	
		}
		
		theCityDAO.deletecity(theCity);
		
		return ResponseEntity.ok().build(); 
	}
	
    //	Delete a Movie
	@Operation(summary = "Delete the Movie by movieID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Deleted the Movie",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = Movie .class)) }),
	})
	@DeleteMapping("/movie/{ID}")
	public ResponseEntity<Movie> deleteMovie(@PathVariable(value= "ID") long ID){
		Movie theMovie = theMovieDAO.findOne(ID);
		if(theMovie == null) {
			return ResponseEntity.notFound().build();
		}
		theMovieDAO.deleteMovie(theMovie);
		return ResponseEntity.ok().build();
	}
	
    //   Delete a theatre
	@Operation(summary = "Delete the Theatre by theatreID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Deleted the Theatre",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = Theatre.class)) }),
	})
	@DeleteMapping("/theatre/{ID}")
	public ResponseEntity<Theatre> deleteTheatre(@PathVariable(value = "ID") long ID){
		Theatre theTheatre = theTheatreDAO.findOne(ID);
		if(theTheatre == null) {
			return ResponseEntity.notFound().build();
		}
		theTheatreDAO.deleteTheatre(theTheatre);
		return ResponseEntity.ok().build();
	}

}










