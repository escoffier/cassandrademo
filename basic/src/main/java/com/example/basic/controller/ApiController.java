package com.example.basic.controller;

import com.example.basic.model.Book;
import com.example.basic.model.Employee;
import com.example.basic.model.MigrationResponse;
import com.example.basic.model.SalariesEntity;
import com.example.basic.repository.nosql.BooksRepository;
import com.example.basic.service.BookService;
import com.example.basic.service.EmployeeService;
import com.example.basic.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ApiController {

    @Autowired
    private BooksRepository booksRepository;

//    @Autowired
//    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private BookService bookService;

    @Autowired
    private SalaryService salaryService;


    @GetMapping("/api/books")
    public List<Book> getBooks() {
        List<Book> books = booksRepository.findAll();
        return books;
    }

    @GetMapping("/api/book/{id}")
    public Optional<Book> getBook(@PathVariable("id") UUID id) {
        return bookService.get(id);
    }

    @GetMapping("/api/employee/{id}")
    public Optional<Employee> getEmployee(@PathVariable Long id, @RequestParam(name = "s", required = false) String source) {

        if ("rdbms".equalsIgnoreCase(source)) {
            return employeeService.getEmployeeFromRdbms(id);
        } else if ("nosql".equalsIgnoreCase(source)) {
            return employeeService.getEmployeeFromNosql(id);
        } else {
            return Optional.empty();
        }
    }

//    @GetMapping("/api/salary/{id}")
//    public Optional<SalariesEntity> getSalary(@PathVariable Long id) {
//        return salaryService.getSalary(id);
//    }

    @GetMapping("/api/salary/{id}")
    public List<SalariesEntity> getSalary(@PathVariable Long id) {
        return salaryService.getSalaries(id);
    }

    @GetMapping("/api/migration/{type}")
    public MigrationResponse spratMigrate(@PathVariable @NotNull String type) {
        if ("employee".equalsIgnoreCase(type)) {
            return employeeService.migrate();
        } else if ("salary".equalsIgnoreCase(type)) {
            return salaryService.migrate();
        } else {
            return new MigrationResponse(0l, "failed");
        }
    }
}
