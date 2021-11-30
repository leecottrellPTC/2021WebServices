package com.leecottrell.lecture1.Rooms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RoomsController {
    static Map<Integer, Rooms> reservations = new HashMap<Integer, Rooms>();
    public RoomsController(){
        Rooms res = new Rooms();
        res.setRoomNum(603);
        res.setGuest("Cottrell Family");
        reservations.put(603, res);

        reservations.put(604, new Rooms(604, "Hertz Family"));
    }

    @RequestMapping(value="/Rooms", method=RequestMethod.GET)
    public ResponseEntity<List<Rooms>> getReservations(@RequestParam(value="roomnum", defaultValue="0") int roomnum){
        List<Rooms> response = new ArrayList<Rooms>();
        switch(roomnum){
            case 0:
                //display all rooms in the reservatins
                response = new ArrayList<Rooms>(reservations.values());
                break;
            default:
                Rooms found = reservations.get(roomnum);
                if(found == null){
                    response.add(new Rooms(roomnum, "room is empty"));
                    return new ResponseEntity<List<Rooms>>(response, HttpStatus.NOT_FOUND);
                }
                else{
                    response.add(reservations.get(roomnum));
                }
                break;
        }//end switch

        return new ResponseEntity<List<Rooms>>(response, HttpStatus.OK);
    }

    @RequestMapping(value="/Rooms", method=RequestMethod.POST)
    public ResponseEntity<Rooms> createReservation(@RequestBody String reservation){

        ObjectMapper mapper = new ObjectMapper();      
        Rooms response = new Rooms(0, "Error in reservation, not added");
        try{
            response = mapper.readValue(reservation, Rooms.class);
            if(reservations.get(response.getRoomNum()) == null){
                 reservations.put(response.getRoomNum(), response);
            }
            else{
                response = new Rooms(0, "Room already reserved");
                return new ResponseEntity<Rooms>(response, HttpStatus.NOT_ACCEPTABLE);
            }        
        }
        catch(JsonMappingException jemx){
    
        } catch (JsonProcessingException e) {
           
        }
        return new ResponseEntity<Rooms>(response, HttpStatus.OK);
    }

    @RequestMapping(value="/Rooms", method=RequestMethod.PUT)
    public ResponseEntity<Rooms> updateReservation(@RequestParam(value="roomnum") int roomnum,
    @RequestParam(value="addon") String addon){

        Rooms response = new Rooms(0, "not implemented");

        if(reservations.get(roomnum) == null){
            response = new Rooms(roomnum, "Room is not reserved, no changes made");
            return new ResponseEntity<Rooms>(response, HttpStatus.NOT_FOUND);
        }

        String origGuest = reservations.get(roomnum).getGuest();
        reservations.get(roomnum).setGuest(origGuest + " and " + addon);
        response = reservations.get(roomnum);

        return new ResponseEntity<Rooms>(response, HttpStatus.OK);
    }

    @RequestMapping(value="/Rooms", method=RequestMethod.DELETE)
    public ResponseEntity<Rooms> deleteReservation(@RequestParam(value="roomnum") int roomnum){

        Rooms response = new Rooms(roomnum, "deleted");

        if(reservations.get(roomnum) == null){
            response = new Rooms(roomnum, "Room is not reserved, not deleted");
            return new ResponseEntity<Rooms>(response, HttpStatus.NOT_FOUND);
        }
        reservations.remove(roomnum);
        return new ResponseEntity<Rooms>(response, HttpStatus.OK);
    }

}
