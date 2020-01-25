package com.footbal.league.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.football.controller.StandingsController;
import com.football.pojos.StandingLeagueResponse;
import com.football.service.StandlingServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class FootballLeagueStandingTest {
	
	@Mock
	private StandlingServiceImpl standingservice;
	
	@InjectMocks
	private StandingsController controller;
	
	@Mock
	StandingLeagueResponse standingmodel;
	
	@Test
	public void ServiceLeagueTest()
	{
		List<StandingLeagueResponse> list=new ArrayList<StandingLeagueResponse>();
		list.add(standingmodel);
		ResponseEntity<List<StandingLeagueResponse>> response=new ResponseEntity<List<StandingLeagueResponse>>(list,HttpStatus.OK);
		
		Mockito.when(standingservice.getStandingLeagueData("abc","test","test")).thenReturn(response);
		int val=controller.getStandingsByCountryName("abc","test","test").getStatusCodeValue();
		assertEquals(200,val);		
		
	}

}
