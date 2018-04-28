package Program;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void playground() throws SQLException {

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3307/sda_jdbc_intro", "root", "PlK3.1415");

        Statement statement = connection.createStatement();
        statement.executeQuery(CREATE_USERS_QUERY);

        connection.createStatement().executeQuery("INSERT INTO users(name, surname, email) VALUES ('michal', 'michalski', 'm.michalski@gmail.com')");
        connection.createStatement().executeQuery("INSERT INTO users(name, surname, email) VALUES ('agata', 'agatowska', 'a.agatowska@gmail.com')");
        connection.createStatement().executeQuery("INSERT INTO users(name, surname, email) VALUES ('lidia', 'lidiowska', 'l.lidiowska@gmail.com')");
        connection.createStatement().executeQuery("INSERT INTO users(name, surname, email) VALUES ('marcin', 'marcinkowski', 'm.marcinkowski@gmail.com')");

        //pobieranie danych z bazy
        ResultSet resultSet = connection.createStatement()
                .executeQuery("SELECT id, name, surname, email FROM users");

        List<User> users = new ArrayList<User>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setSurname(resultSet.getString("surname"));
            user.setEmail(resultSet.getString("email"));
            users.add(user);

        }

        users.forEach(System.out::println);

        ResultSet rs = connection.createStatement()
                .executeQuery("SELECT COUNT(name) FROM users");

        rs.next();
        int count = rs.getInt(1);

        System.out.println("Mamy " + count + " pracownikow");

        //UPDATE - zaktualizować uzytkownikowi o podanym id jego adres email
        connection.createStatement().executeUpdate("UPDATE USERS SET EMAIL = 'buziaczek@wp.pl' WHERE ID = 16");


        //DELETE - usunąć uzytkownika marcinkowski
        connection.createStatement().executeUpdate("DELETE FROM users WHERE id = 16");


    }

    private static final String CREATE_USERS_QUERY = "CREATE TABLE IF NOT EXISTS USERS(id int primary key AUTO_INCREMENT, name varchar(255), surname varchar(255), email varchar(255))";

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3307/sda_jdbc_intro", "root", "PlK3.1415");


        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj sowje imie");
        String name = scanner.nextLine();
        System.out.println("Podaj sowje nazwisko");
        String surname = scanner.nextLine();

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, surname) VALUES (?, ?)");

        preparedStatement.setString(1, name);
        preparedStatement.setString(2, surname);
        preparedStatement.execute();

    }

}
