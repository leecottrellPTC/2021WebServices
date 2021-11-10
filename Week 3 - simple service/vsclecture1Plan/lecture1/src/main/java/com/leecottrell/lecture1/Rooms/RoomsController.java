package com.leecottrell.lecture1.Rooms;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/Rooms", method = RequestMethod.GET)
    public ResponseEntity<List<Rooms>> getReservations(@RequestParam(value = "roomnum", defaultValue = "0")
         int roomnum) {
        // Collection<Rooms> results = reservations.values();
        // List response = new ArrayList<>(results);
        List response = new ArrayList();
        switch (roomnum) {
        case 0:
            response = new ArrayList(reservations.values());
            break;
        default:
            Rooms found = reservations.get(roomnum);
            if (found == null) {
                response.add(new Rooms(roomnum, "room is empty"));
                return new ResponseEntity<List<Rooms>>(response, HttpStatus.NOT_FOUND);
            } else {
                response.add(reservations.get(roomnum));
            }
            break;
        }// end switch
        return new ResponseEntity<List<Rooms>>(response, HttpStatus.OK);
    }// end get

    
    @RequestMapping(value = "/Rooms", method = RequestMethod.POST)
    public ResponseEntity<Rooms> postReservation(@RequestBody String reservation) {
        ObjectMapper mapper = new ObjectMapper();
        Rooms response = new Rooms(0, "Error in reservation, not added");
        try {
            response = mapper.readValue(reservation, Rooms.class);
            if (reservations.get(response.getRoomNum()) == null) {
                reservations.put(response.getRoomNum(), response);
            }
            else{
                
                response = new Rooms(0, "Room already reserved");
                return new ResponseEntity<Rooms>(response, HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new ResponseEntity<Rooms>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/Rooms", method = RequestMethod.PUT)
    public ResponseEntity<Rooms> updateReservation(@RequestParam(value="roomnum") int roomnum,
        @RequestParam(value="addon") String addon) {
        Rooms response = new Rooms(0, "empty");

        if(reservations.get(roomnum) == null){
            response = new Rooms(0, "room not reserved");
                return new ResponseEntity<Rooms>(response, HttpStatus.NOT_FOUND);
        }
        String origGuest = reservations.get(roomnum).getGuest();
        reservations.get(roomnum).setGuest(origGuest + " and " + addon);
        response = reservations.get(roomnum);

        return new ResponseEntity<Rooms>(response, HttpStatus.OK);

    }

    @RequestMapping(value = "/Rooms", method = RequestMethod.DELETE)
    public ResponseEntity<Rooms> deleteReservation(@RequestParam(value="roomnum") int roomnum) {
        Rooms response = new Rooms(0, "empty");

        if(reservations.get(roomnum) == null){
            response = new Rooms(0, "room not reserved");
                return new ResponseEntity<Rooms>(response, HttpStatus.NOT_FOUND);
        }
        response = new Rooms(roomnum, "deleted");
        reservations.remove(roomnum);

        return new ResponseEntity<Rooms>(response, HttpStatus.OK);

    }
}// end class
