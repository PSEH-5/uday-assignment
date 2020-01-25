package com.football.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.football.pojos.StandingLeagueResponse;

public interface StandingsService {

	public String getCountryByName(String countryName);
	public String getLeagueByCountryIdAndLeagueName(String countryId, String leagueName);
	public abstract ResponseEntity<List<StandingLeagueResponse>> getStandingLeagueData(String countryName,String teamName,String leagueName);
}
