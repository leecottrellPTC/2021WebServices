package com.leecottrell.testwebservicelecture.Sport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SportController {
    @RequestMapping(value="/Sport", method=RequestMethod.GET)
    public ResponseEntity<Sport> sportInfo(@RequestParam String sportName){
        Sport aSport = new Sport();
        //aSport.setTeamName("Riverhounds");
        
        if(sportName.equalsIgnoreCase("soccer")){
            aSport.setSportName(sportName);
            aSport.setStadium("Highmark Stadium");
            aSport.setTeamName("Riverhounds");
        }
        else if(sportName.equalsIgnoreCase("hockey")){
            aSport.setSportName(sportName);
            aSport.setStadium("PPG Paints");
            aSport.setTeamName("Penguins");
        }
        else{
            aSport.setSportName(sportName);
            aSport.setTeamName("no name");
            aSport.setStadium("no stadium");
            return new ResponseEntity<Sport>(aSport, HttpStatus.NOT_FOUND);
        }
        

        return new ResponseEntity<Sport>(aSport, HttpStatus.OK);
    }
    
}
