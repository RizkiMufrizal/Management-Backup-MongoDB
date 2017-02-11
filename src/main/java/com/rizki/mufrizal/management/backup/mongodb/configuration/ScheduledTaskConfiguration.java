package com.rizki.mufrizal.management.backup.mongodb.configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since Feb 11, 2017
 * @Time 9:45:20 PM
 * @Encoding UTF-8
 * @Project Management-Backup-MongoDB
 * @Package com.rizki.mufrizal.management.backup.mongodb.configuration
 *
 */
@EnableScheduling
@Component
public class ScheduledTaskConfiguration {

    @Autowired
    private DataSource dataSource;

    private Connection connection;

    private PreparedStatement preparedStatementSelectUser;
    private PreparedStatement preparedStatementSelectUserRole;

    private PreparedStatement preparedStatementInsertUser;
    private PreparedStatement preparedStatementInsertUserRole;

    private final String selectTableUserSQL = "SELECT * FROM users WHERE username = ?";
    private final String selectTableUserRoleSQL = "SELECT * FROM user_roles WHERE username = ?";

    private final String insertTableUserSQL = "INSERT INTO users(username, password, enabled) values(?, ?, ?)";
    private final String insertTableUserRoleSQL = "INSERT INTO user_roles(user_role_id, role, username) values(?, ?, ?)";

    @Scheduled(fixedRate = 3600000)
    public void insertDefaultUserAdministrator() throws SQLException {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        connection = dataSource.getConnection();

        preparedStatementSelectUser = connection.prepareStatement(selectTableUserSQL);
        preparedStatementSelectUser.setString(1, "admin");
        ResultSet resultSetUser = preparedStatementSelectUser.executeQuery();

        preparedStatementSelectUserRole = connection.prepareStatement(selectTableUserRoleSQL);
        preparedStatementSelectUserRole.setString(1, "admin");
        ResultSet resultSetUserRole = preparedStatementSelectUserRole.executeQuery();

        if (!resultSetUser.next() && !resultSetUserRole.next()) {
            preparedStatementInsertUser = connection.prepareStatement(insertTableUserSQL);
            preparedStatementInsertUser.setString(1, "admin");
            preparedStatementInsertUser.setString(2, bCryptPasswordEncoder.encode("admin"));
            preparedStatementInsertUser.setBoolean(3, Boolean.TRUE);
            preparedStatementInsertUser.executeUpdate();

            preparedStatementInsertUserRole = connection.prepareStatement(insertTableUserRoleSQL);
            preparedStatementInsertUserRole.setString(1, UUID.randomUUID().toString());
            preparedStatementInsertUserRole.setString(2, "ROLE_ADMINISTRATOR");
            preparedStatementInsertUserRole.setString(3, "admin");
            preparedStatementInsertUserRole.executeUpdate();

            preparedStatementInsertUser.close();
            preparedStatementInsertUserRole.close();
            preparedStatementInsertUser.clearParameters();
            preparedStatementInsertUserRole.clearParameters();

            preparedStatementInsertUser = connection.prepareStatement(insertTableUserSQL);
            preparedStatementInsertUser.setString(1, "user");
            preparedStatementInsertUser.setString(2, bCryptPasswordEncoder.encode("user"));
            preparedStatementInsertUser.setBoolean(3, Boolean.TRUE);
            preparedStatementInsertUser.executeUpdate();

            preparedStatementInsertUserRole = connection.prepareStatement(insertTableUserRoleSQL);
            preparedStatementInsertUserRole.setString(1, UUID.randomUUID().toString());
            preparedStatementInsertUserRole.setString(2, "ROLE_USER");
            preparedStatementInsertUserRole.setString(3, "user");
            preparedStatementInsertUserRole.executeUpdate();

            preparedStatementInsertUser.close();
            preparedStatementInsertUserRole.close();
        }

        preparedStatementSelectUser.close();
        preparedStatementSelectUserRole.close();
        connection.close();

    }

}
