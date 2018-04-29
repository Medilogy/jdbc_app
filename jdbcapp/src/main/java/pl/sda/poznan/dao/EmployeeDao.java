package pl.sda.poznan.dao;

import pl.sda.poznan.model.Employee;

import java.util.List;

public interface EmployeeDao {

    Employee getById(int id);

    List<Employee> getEmployees();

    List<Employee> getEmployeesByName(String name);

    List<Employee> getEmployeesBySurname(String surname);

    int count();

    boolean save(Employee employee);

    boolean delete(int id);

    boolean update();
}