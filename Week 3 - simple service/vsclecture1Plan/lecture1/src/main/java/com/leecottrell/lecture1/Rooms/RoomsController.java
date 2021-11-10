package com.leecottrell.lecture1.Rooms;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RoomsController {

    static Map<Integer, Rooms> reservations = new HashMap();

    public RoomsController() {
        Rooms res = new Rooms();
        res.setRoomNum(603);
        res.setGuest("Cottrell Family");
        reservations.put(res.getRoomNum(), res);

        Rooms res2 = new Rooms();
        res2.setRoomNum(604);
        res2.setGuest("Hertz Family");
        reservations.put(res2.getRoomNum(), res2);
    }

    @RequestMapping(value="/Rooms", method=RequestMethod.GET)
	public List<Rooms> getReservations(@RequestParam(value="roomnum",defaultValue="0") int roomnum ) {
        //Collection<Rooms> results = reservations.values();
        //List response = new ArrayList<>(results);
        List response = new ArrayList();

        switch(roomnum){
            case 0:
                response = new ArrayList(reservations.values());
                break;
            default:
                Rooms found = reservations.get(roomnum);
                if(found == null){
                    response.add(new Rooms(roomnum, "room is empty"));
                }
                else{
                    response.add(reservations.get(roomnum));
                }
                break;
        }//end switch
        
        return response;
    }// end get

    @RequestMapping(value = "/Rooms", method = RequestMethod.POST)
    public Rooms postReservation(@RequestBody String reservation) {
        ObjectMapper mapper = new ObjectMapper();
               
        Rooms response = new Rooms(0, "Error in reservation, not added");
        try {
            response = mapper.readValue(reservation, Rooms.class);
            reservations.put(response.getRoomNum(), response);
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return response;

    }

    @RequestMapping(value = "/Rooms", method = RequestMethod.PUT)
    @ResponseStatus(code = HttpStatus.NOT_IMPLEMENTED, reason = "Not configured yet")
    public Rooms updateReservation() {
        Rooms response = new Rooms(0, "empty");

        return response;

    }

    @RequestMapping(value = "/Rooms", method = RequestMethod.DELETE)
    @ResponseStatus(code = HttpStatus.NOT_IMPLEMENTED, reason = "Not configured yet")
    public Rooms deleteReservation() {
        Rooms response = new Rooms(0, "empty");

        return response;

    }
}// end class
