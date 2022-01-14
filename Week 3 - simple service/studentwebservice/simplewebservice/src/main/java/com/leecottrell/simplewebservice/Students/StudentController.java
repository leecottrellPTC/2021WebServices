package com.leecottrell.simplewebservice.Students;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class StudentController {
    static Map<Integer, Student> studs = new HashMap<Integer, Student>();

    public StudentController() {
        Student aStudent = new Student();
        aStudent.setStudentID(42);
        aStudent.setFname("Douglas");
        aStudent.setLname("Adams");
        studs.put(aStudent.getStudentID(), aStudent);
      
        aStudent = new Student();
        aStudent.setStudentID(7);
        aStudent.setFname("Heinrich");
        aStudent.setLname("Harrer");
        studs.put(aStudent.getStudentID(), aStudent);

        aStudent = new Student();
        aStudent.setStudentID(451);
        aStudent.setFname("Ray");
        aStudent.setLname("Bradbury");
        studs.put(aStudent.getStudentID(), aStudent);

        aStudent = new Student();
        aStudent.setStudentID(80);
        aStudent.setFname("Jules");
        aStudent.setLname("Verne");
        studs.put(aStudent.getStudentID(), aStudent);
    }

    @RequestMapping(value = "/Students", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getStudents(@RequestParam(value = "studId", defaultValue = "0") int studId) {
        // Collection<Student> results = studs.values();
        List response = new ArrayList<Student>();

        switch (studId) {
            case 0:
                response = new ArrayList<Student>(studs.values());
                break;
            default:
                Student found = studs.get(studId);
                if (found == null) {
                    response.add(new Student(studId, "Not", "Found"));
                    return new ResponseEntity<List<Student>>(response, HttpStatus.NOT_FOUND);
                } else {
                    response.add(studs.get(studId));
                }
                break;
        }

        return new ResponseEntity<List<Student>>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/Students", method = RequestMethod.POST)
    public ResponseEntity<Student> postStudent(@RequestBody String newStudent) {
        ObjectMapper mapper = new ObjectMapper();
        Student response = new Student(0, "Invalid", "Student");

        try {
            response = mapper.readValue(newStudent, Student.class);
            if (studs.get(response.getStudentID()) == null) {
                studs.put(response.getStudentID(), response);
            }
            else{
                response = new Student(response.getStudentID(), "Student", "Already in database");
                return new ResponseEntity<Student>(response, HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (JsonMappingException e) {

        } catch (JsonProcessingException ex) {

        }

        return new ResponseEntity<Student>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/Students", method = RequestMethod.PUT)
    public ResponseEntity<Student> putStudent(@RequestParam(value="studentId") int studentId,
    @RequestParam(value="fname") String fname, @RequestParam(value="lname") String lname) {
        Student response = new Student(0, "no", "Student");

        if(studs.get(studentId) == null){
            return new ResponseEntity<Student>(response, HttpStatus.NOT_FOUND);
        }
        else{
            //studs.get(studentId).setFname(fname);
            //studs.get(studentId).setLname(lname);

            response = new Student(studentId, fname, lname);
            studs.remove(studentId);
            studs.put(studentId, response);
        }

        return new ResponseEntity<Student>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/Students", method = RequestMethod.DELETE)
    @ResponseStatus(code = HttpStatus.NOT_IMPLEMENTED, reason = "Not Configured yet")
    public String deleteStudent() {
        return "Delete Not Implemented Yet";
    }
}
