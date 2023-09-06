package com.sumit.movieticketbooking.controller;

import java.util.List;

//import javax.validation.Valid;

import com.sumit.movieticketbooking.model.*;
import com.sumit.movieticketbooking.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@Description : Controller for end users to perform various operations while booking movie tickets
 */
@RestController
@RequestMapping("/api/v1/user")
public class EndUserController {

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

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    //	Get the list of City where you can book the movie
    @Operation(summary = "Get the list of City where you can book the movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the List of City",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))}),
    })
    @GetMapping("/city")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<City> getAllCity() {

        return theCityDAO.getCity();
    }


    //	Get the list of theatre available in a City
    @Operation(summary = "Get the list of theatres available in a City")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the List of Theatre",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))}),
    })
    @GetMapping("/{ID}/theatre")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Theatre> getTheatreInCity(@PathVariable(value = "ID") long ID) {

        City theCity = theCityDAO.findOne(ID);

        return theTheatreDAO.getTheatreByCityId(theCity);
    }

    //	Get the list of Movies available in the theatre
    @Operation(summary = "Get the list of Movies available in the theatre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the List of Movies available in a thretre",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))}),
    })
    @GetMapping("/theatre/{ID}/movie")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Object[]> getMovieByTheatreID(@PathVariable(value = "ID") long ID) {
        List<Object[]> moviesList = null;
        try {
            moviesList = theMovieDAO.getMovieByTheatreId(ID);
        } catch (Exception ex) {
            System.out.println("message = "+ex.getMessage()+"\ncause : "+ex.getCause());
            throw new RuntimeException(ex);
        }
        return moviesList;
    }

    //	Get the Available Shows for a particular movie
    @Operation(summary = "Get the Available Shows for a particular movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Available Shows for a particular movie",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))}),
    })
    @GetMapping("/movie/{id}/show")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Show> geShowByMovieId(@PathVariable(value = "id") long id) {

        Movie theMovie = theMovieDAO.findOne(id);

        return theShowDAO.fetchByMovie(theMovie);
    }

    //	Get the seats available for a particular Show using the show ID
    @Operation(summary = "Get the seats available for a particular Show using the show ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the seats available for a particular Show using the show ID",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Booking.class))}),
    })
    @GetMapping("/show/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Booking getAvailableSeat(@PathVariable(value = "id") long id) {
        return theBookingsDAO.getAvailableSeat(id);
    }

    //	Book a Seat using the show id By passing the show POJO to the API
    @Operation(summary = "Book a Seat using the show id By passing the booking request to the API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booked the seats against a Show",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Booking.class))}),
    })
    @PostMapping("show/{id}/bookings")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Booking bookSeatForShow(@PathVariable(value = "id") long id, @RequestBody Booking b) {
        b.setShow_id(id);
        return theBookingsDAO.bookTheSeat(b);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUserName(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtUtil.generateToken(authRequest.getUserName());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}




