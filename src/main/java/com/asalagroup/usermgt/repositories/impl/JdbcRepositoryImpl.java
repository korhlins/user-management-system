package com.asalagroup.usermgt.repositories.impl;

import com.asalagroup.usermgt.commons.Gender;
import com.asalagroup.usermgt.entities.User;
import com.asalagroup.usermgt.repositories.UserMgtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class JdbcRepositoryImpl implements UserMgtRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public User createUser(User user){
        String query = """
        insert into as_user(first_name, last_name, middle_name, dob, email_address, mobile_number, gender, country, state, city, address) 
        values ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s','%s','%s')
        """;
        query = String.format(query, user.getFirstName(), user.getLastName(), user.getMiddleName(), user.getDateOfBirth(), user.getEmailAddress(), user.getMobileNumber(), user.getGender(), user.getCountry(), user.getState(), user.getCity(), user.getResidentialAddress());

        jdbcTemplate.execute(query);
        return user;
    }

    @Override
    public User findUserByEmail(String email){
        String query = """
                select * from as_user where email_address = '%s'
                """;
        query = String.format(query, email);
        List<User> users = jdbcTemplate.query(query, getUserRowMapper());
        return users.stream().findFirst().orElse(null);
    }

    @Override
    public User findUserByMobileNumber(String mobileNumber){
        String query = """
                select * from as_user where mobile_number = '%s'
                """;
        query = String.format(query, mobileNumber);
        List<User> users = jdbcTemplate.query(query, getUserRowMapper());
        return users.stream().findFirst().orElse(null);
    }

    @Override
    public User updateExistingUser(String formerEmail, String newFirstName, String newMobileNumber, String newEmail){

        String query = """
                 update as_user 
                 set first_name = '%s', mobile_number = '%s', email_address = '%s' 
                 where  email_address = '%s'
                 """;
         query = String.format(query, newFirstName, newMobileNumber, newEmail, formerEmail);
         jdbcTemplate.update(query);
         User user = findUserByEmail(newEmail);
        return user;
    }

    @Override
    public void deleteAllUsers(){
        String query = """
                delete from as_user
                """;
        jdbcTemplate.execute(query);
    }




    public RowMapper<User> getUserRowMapper(){
        return (rs, rowNum) -> {
            User user = new User();
            user.setId((long) rowNum);
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setMiddleName(rs.getString("middle_name"));
            user.setDateOfBirth(rs.getDate("dob").toLocalDate());
            user.setEmailAddress(rs.getString("email_address"));
            user.setMobileNumber(rs.getString("mobile_number"));
            user.setGender(Gender.valueOf(rs.getString("gender")));
            user.setCountry(rs.getString("country"));
            user.setState(rs.getString("state"));
            user.setCity(rs.getString("city"));
            user.setResidentialAddress(rs.getString("address"));
            return user;
        };
    }





}
