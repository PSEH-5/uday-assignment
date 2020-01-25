package com.football.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.football.exception.FootballException;
import com.football.pojos.StandingLeagueResponse;
import com.football.service.StandingsService;
import com.football.validators.StandingsValidator;

@RestController
public class StandingsController {

	@Autowired
	private StandingsService serviceleague;

	@RequestMapping(value = "/getstandings/{countryName}/{leagueName}/{teamName}", method = RequestMethod.GET)
	public ResponseEntity<List<StandingLeagueResponse>> getStandingsByCountryName(@PathVariable String countryName, 
			@PathVariable String leagueName, 
			@PathVariable String teamName ) throws FootballException
	{
		
			return serviceleague.getStandingLeagueData(countryName,teamName,leagueName);
	}	

}

