package pl.sda.poznan.dao;

import pl.sda.poznan.model.Employee;
import pl.sda.poznan.util.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BaseEmployeeDao implements EmployeeDao {

    private Connection connection = DbUtils.getConnection();

    // todo
    public Employee getById(int id) {
        try {
            PreparedStatement stmt = connection
                    .prepareStatement("SELECT id, name, surname, hire_date, salary "
                            + "FROM employee "
                            + "WHERE ID = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            Employee employee = new Employee();
            employee.setId(rs.getInt(1));
            employee.setName(rs.getString(2));
            employee.setSurname(rs.getString(3));
            employee.setHireDate(rs.getDate(4));
            employee.setSalary(rs.getDouble(5));
            return employee;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // todo
    public List<Employee> getEmployees() {
        return null;
    }

    public List<Employee> getEmployeesByName(String name) {
        return null;
    }

    public List<Employee> getEmployeesBySurname(String surname) {
        return null;
    }

    // todo
    public int count() {
        return 0;
    }

    // todo
    public boolean save(Employee employee) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into employee(name, surname, salary, hire_date) values (?, ?, ?, ?)");
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getSurname());
            preparedStatement.setDouble(3, employee.getSalary());
            preparedStatement.setDate(4, new java.sql.Date(employee.getHireDate().getTime()));
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // todo
    public boolean delete(int id) {
        return false;
    }

    public boolean update() {
        return false;
    }
}
