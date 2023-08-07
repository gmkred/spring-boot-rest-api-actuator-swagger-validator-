package net.gmkr.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.gmkr.springboot.bean.Student;

@RestController
@RequestMapping("/students")
public class StudentController {

	@GetMapping("/student")
	public ResponseEntity<Student> getStudent() {
		Student student = new Student(1, "Malli", "Reddy");
		return ResponseEntity.ok().header("custom-header", "Malli").body(student);
	}

	@GetMapping("/")
	public ResponseEntity<List<Student>> getStudents() {
		List<Student> students = new ArrayList<>();
		students.add(new Student(1, "Malli", "Reddy"));
		students.add(new Student(1, "Arjun", "Reddy"));
		return ResponseEntity.ok(students);
		// [{"id":1,"firstName":"Malli","lastName":"Reddy"},{"id":1,"firstName":"Arjun","lastName":"Reddy"}]
	}

	// spring boot rest api with path variable
	// {id} -URI template variable
	@GetMapping("/{id}")
	public ResponseEntity<Student> studentPathVariable(@PathVariable int id) {
		return ResponseEntity.ok(new Student(id, "Malli", "Reddy"));
	}

	// http://localhost:8080/student/query?id=1
	@GetMapping("/query")
	public ResponseEntity<Student> studentRequestParam(@RequestParam("id") int id) {
		return ResponseEntity.ok(new Student(id, "Malli", "Reddy"));
	}

	// spring boot rest api that handles HTTP POST REQUEST
	@PostMapping("/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		System.out.println(student.getId());
		System.out.println(student.getFirstName());
		return new ResponseEntity<>(student, HttpStatus.CREATED);
	}

	@PutMapping("/{id}/update")
	public ResponseEntity<Student> update(@RequestBody Student student, @PathVariable int id) {
		System.out.println(student.getId());
		System.out.println(student.getFirstName());
		return ResponseEntity.ok(student);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") int id) {

		return ResponseEntity.ok(true);
	}
}
