/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author EvangelineZ
 */
public class DBController {

    Connection con;
    Statement statement;
    ResultSet resultSet;

    ///////////////////////////////////Login////////////////////////////////////
    public boolean validateLogin(String username, String password) throws ClassNotFoundException, SQLException {

        boolean found = false;

        String query = "SELECT * FROM users";

        try {
            selectQuery(query);
            String pass = resultSet.getString("password");
            boolean exist = idExist(username);
            //check if username and password match
            if (exist == true) {
                found = password.equals(pass);
            } else {
                found = false;
            }//if

        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }
        return found;
    }//method

    public Member getMember(String id) {

        Member member = new Member();

        String query = "SELECT * FROM members WHERE id = '" + id + "'";

        try {
            selectQuery(query);
            member.setId(resultSet.getString("id"));
            member.setName(resultSet.getString("name"));
            member.setAddress(resultSet.getString("address"));
            member.setDob(resultSet.getDate("dob"));
            member.setDor(resultSet.getDate("dor"));
            member.setStatus(resultSet.getString("status"));
            member.setBalance(resultSet.getFloat("balance"));
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }

        return member;
    }//method

    public boolean idExist(String user) {
        boolean exist = false;
        String query = "SELECT * FROM users";
        try {
            selectQuery(query);
            while (resultSet.next()) {
                if (user.equals(resultSet.getString("id"))) {
                    exist = true;
                    break;
                } else {
                    exist = false;
                }
            }
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }
        return exist;
    }//method

    public void registerMember(Member member, String password) {
        PreparedStatement ps = null;
        Date currentDate = new Date(Calendar.getInstance().getTime().getTime());

        try {
            ps = con.prepareStatement("INSERT INTO members VALUE (?,?,?,?,?,?,?)" + PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, member.getId());
            ps.setString(2, member.getName());
            ps.setString(3, member.getAddress());
            ps.setDate(4, member.getDob());
            ps.setDate(5, currentDate);
            ps.setString(6, "PROVIS");
            ps.setFloat(7, member.getBalance());

            ps.executeUpdate();
            ps.close();

            ps = con.prepareStatement("INSERT INTO users VALUE (?,?,?)" + PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, member.getId());
            ps.setString(2, password);
            ps.setString(3, "PROVIS");

            ps.executeUpdate();
            ps.close();

        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }
    }

    /////////////////////////////////Members////////////////////////////////////
    public ArrayList paymentList(String id) {
        ArrayList paymentList = new ArrayList();
        String query = "SELECT * from claims WHERE mem_id ='" + id + "'";
        try {
            selectQuery(query);
            while (resultSet.next()) {
                Payment payment = new Payment();
                payment.setId(resultSet.getInt("id"));
                payment.setMemId(resultSet.getString("mem_id"));
                payment.setPaymentType(resultSet.getString("type_of_payment"));
                payment.setAmount(resultSet.getFloat("amount"));
                payment.setDate(resultSet.getDate("date"));

                paymentList.add(payment);
            }
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }
        return paymentList;
    }

    public void makePayment(String id, float amount, String type) {
        PreparedStatement ps = null;
        Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
        try {
            ps = con.prepareStatement("INSERT INTO payments VALUES (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(2, id);
            ps.setString(3, type);
            ps.setFloat(4, amount);
            ps.setDate(5, currentDate);

            ps.executeUpdate();
            ps.close();

        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }
    }

    public void submitClaim(String id, float amount, String rationale) {
        PreparedStatement ps = null;
        Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
        try {
            ps = con.prepareStatement("INSERT INTO claims VALUES (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(2, id);
            ps.setDate(3, currentDate);
            ps.setString(4, rationale);
            ps.setString(5, "PENDING");
            ps.setFloat(6, amount);

            ps.executeUpdate();
            ps.close();

        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }
    }//method

    ////////////////////////////////Admin///////////////////////////////////////
    public ArrayList memberList() {
        ArrayList memberList = new ArrayList();
        String query = "SELECT * from members WHERE mem_id NOT LIKE 'ADMIN'";
        try {
            selectQuery(query);
            while (resultSet.next()) {
                Member member = new Member();
                selectQuery(query);
                member.setId(resultSet.getString("id"));
                member.setName(resultSet.getString("name"));
                member.setAddress(resultSet.getString("address"));
                member.setDob(resultSet.getDate("dob"));
                member.setDor(resultSet.getDate("dor"));
                member.setStatus(resultSet.getString("status"));
                member.setBalance(resultSet.getFloat("balance"));
                memberList.add(member);
            }
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }
        return memberList;
    }//method
    
    public ArrayList balanceList(){
        ArrayList balanceList = new ArrayList();
        String query = "SELECT * from members WHERE balance<0.00";
        try {
            selectQuery(query);
            while (resultSet.next()) {
                Member member = new Member();
                selectQuery(query);
                member.setId(resultSet.getString("id"));
                member.setName(resultSet.getString("name"));
                member.setAddress(resultSet.getString("address"));
                member.setDob(resultSet.getDate("dob"));
                member.setDor(resultSet.getDate("dor"));
                member.setStatus(resultSet.getString("status"));
                member.setBalance(resultSet.getFloat("balance"));
                balanceList.add(member);
            }
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }
        return balanceList;
    }//method

    public ArrayList claimList(String id) {
        ArrayList claimList = new ArrayList();
        String query = "SELECT * from claims WHERE mem_id LIKE'" + id + "'";
        try {
            selectQuery(query);
            while (resultSet.next()) {
                Claim claim = new Claim();

                claim.setAmount(resultSet.getFloat("amount"));
                claim.setDate(resultSet.getDate("date"));
                claim.setId(resultSet.getInt("id"));
                claim.setMemId(resultSet.getString("mem_id"));
                claim.setRationale(resultSet.getString("rationale"));
                claim.setStatus(resultSet.getString("status"));
                claimList.add(claim);
            }
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }
        return claimList;
    }

    private void selectQuery(String query) {
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }
    }
}//class
