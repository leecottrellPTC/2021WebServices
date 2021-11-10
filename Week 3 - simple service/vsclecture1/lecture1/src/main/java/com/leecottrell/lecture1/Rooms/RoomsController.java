package com.leecottrell.lecture1.Rooms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ResponseStatus(code = HttpStatus.NOT_IMPLEMENTED,reason="POST not configured")
    public Rooms createReservation(){

        Rooms response = new Rooms(0, "not implemented");
        return response;
    }

    @RequestMapping(value="/Rooms", method=RequestMethod.PUT)
    @ResponseStatus(code = HttpStatus.NOT_IMPLEMENTED,reason="PUT not configured")
    public Rooms updateReservation(){

        Rooms response = new Rooms(0, "not implemented");
        return response;
    }

    @RequestMapping(value="/Rooms", method=RequestMethod.DELETE)
    @ResponseStatus(code = HttpStatus.NOT_IMPLEMENTED,reason="DELETE not configured")
    public Rooms deleteReservation(){

        Rooms response = new Rooms(0, "not implemented");
        return response;
    }

}
