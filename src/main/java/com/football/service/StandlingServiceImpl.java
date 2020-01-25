package com.football.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.football.exception.FootballException;
import com.football.pojos.Country;
import com.football.pojos.CountryInfoResponse;
import com.football.pojos.FootballLeagueConstants;
import com.football.pojos.League;
import com.football.pojos.StandingLeagueResponse;
import com.football.validators.StandingsValidator;

@Service
public class StandlingServiceImpl implements StandingsService{

	@Value( "${api.host}" )
	private String host;

	@Value( "${api.key}" )
	private String apikey;

	@Autowired
	StandingsValidator standingsValidator;
	
	@Override
	public ResponseEntity<List<StandingLeagueResponse>> getStandingLeagueData(String countryName,String teamName, String leagueName) {

		//if(standingsValidator.validate_getStandingsByCountryName(countryName,teamName,leagueName))
		{
			RestTemplate resttemplate=new RestTemplate();
			String countryId=getCountryByName(countryName);
			List<StandingLeagueResponse> list=new ArrayList<StandingLeagueResponse>();
			if(!StringUtils.isEmpty(countryId))
			{
				String LeagueId=getLeagueByCountryIdAndLeagueName(countryId,leagueName);
				if(!StringUtils.isEmpty(LeagueId))
				{
					String standingUrl=host+"/?action=get_standings&league_id="+LeagueId+"&APIkey="+apikey;
					ResponseEntity<Object[]> standingresponse=resttemplate.getForEntity(standingUrl,Object[].class);
					JSONArray jsonArray = new JSONArray(standingresponse.getBody());
					StandingLeagueResponse leagueResponse=null;
					for(int i=0;i<jsonArray.length();i++)
					{
						JSONObject jsonObject1 = jsonArray.getJSONObject(i);
						leagueResponse=new StandingLeagueResponse();
						leagueResponse.setCountry_id(countryId);
						leagueResponse.setCountry_name(jsonObject1.optString("country_name"));
						leagueResponse.setLeague_name(jsonObject1.optString("league_name"));
						leagueResponse.setLegaue_id(jsonObject1.optString("league_id"));
						leagueResponse.setTeam_id(jsonObject1.optString("team_id"));
						leagueResponse.setTeam_name(jsonObject1.optString("team_name"));
						leagueResponse.setOverall_league_position(jsonObject1.optString("overall_league_position"));
						list.add(leagueResponse);
					}
					return new ResponseEntity<List<StandingLeagueResponse>>(list,HttpStatus.OK);
				}
			}
			return new ResponseEntity<List<StandingLeagueResponse>>(list,HttpStatus.OK);
		}
		//return new ResponseEntity<List<StandingLeagueResponse>>(HttpStatus.BAD_REQUEST);
	}

	@Override
	public String getLeagueByCountryIdAndLeagueName(String countryId, String leagueName)
	{
		if(!StringUtils.isEmpty(countryId))
		{
			RestTemplate resttemplate=new RestTemplate();
			String leagueUrl=host+"/?action=get_leagues&country_id="+countryId+"&APIkey="+apikey;
			System.out.println(leagueUrl);
			ResponseEntity<League[]> responseLeagueList=resttemplate.getForEntity(leagueUrl, League[].class);
			for(League league:responseLeagueList.getBody())
			{
				if(leagueName.equalsIgnoreCase(league.getLeague_name().trim()))
				{
					return league.getLeague_id();
				}
			}
		}
		return null;
	}

	@Override
	public String getCountryByName(String countryName)
	{
		if(!StringUtils.isEmpty(countryName))
		{
			RestTemplate resttemplate=new RestTemplate();
			String getcountryurl=host+"/?action=get_countries&APIkey="+apikey;
			ResponseEntity<Country[]> responsecountryList=resttemplate.getForEntity(getcountryurl,Country[].class);
			for(Country country:responsecountryList.getBody())
			{
				if(countryName.equalsIgnoreCase(country.getCountry_name()))
				{
					return country.getCountry_id();
				}
			}
		}
		return null;

	}

}
