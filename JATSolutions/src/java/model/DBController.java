/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
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
    LocalDate now = LocalDate.now();
    String startOfYear = now.with(TemporalAdjusters.firstDayOfYear()).toString();
    String endOfYear = now.with(TemporalAdjusters.lastDayOfYear()).toString();
    String today = now.toString();

    public DBController(Connection con) {
        this.con = con;
    }

    ///////////////////////////////////Login////////////////////////////////////
    public boolean validateLogin(String username, String password) throws ClassNotFoundException, SQLException {

        boolean found = false;

        String query = "SELECT * FROM users";

        try {
            selectQuery(query);
            while (resultSet.next() && found == false) {
                if (username.equals(resultSet.getString("id"))) {
                    if (password.equals(resultSet.getString("password"))) {
                        found = true;
                    }
                }
            }

        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }
        return found;
    }//method

    public Member getMember(String username) {

        Member member = new Member();

        String query = "SELECT * FROM members WHERE id = '" + username + "'";

        try {
            suspendMember();
            selectQuery(query);
            while (resultSet.next()) {
                member.setId(resultSet.getString("id"));
                member.setName(resultSet.getString("name"));
                member.setAddress(resultSet.getString("address"));
                member.setDob(resultSet.getDate("dob"));
                member.setDor(resultSet.getDate("dor"));
                member.setStatus(resultSet.getString("status"));
                member.setBalance(resultSet.getFloat("balance"));
            }
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }

        return member;
    }//method

    public boolean checkAdmin(String username) {

        boolean admin = false;
        String query = "SELECT id FROM users WHERE status ='ADMIN'";
        try {
            selectQuery(query);
            while (resultSet.next()) {
                if (username.equals(resultSet.getString(1))) {
                    admin = true;
                    break;
                } else {
                    admin = false;
                }
            }
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }
        return admin;
    }//method
/////////////////////////////////Registration///////////////////////////////////

    public boolean idExist(String username) {

        boolean exist = false;
        String query = "SELECT * FROM users";
        try {
            selectQuery(query);
            while (resultSet.next()) {
                if (username.equals(resultSet.getString("id"))) {
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
            ps = con.prepareStatement("INSERT INTO members VALUES (?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, member.getId());
            ps.setString(2, member.getName());
            ps.setString(3, member.getAddress());
            ps.setDate(4, member.getDob());
            ps.setDate(5, currentDate);
            ps.setString(6, "APPLIED");
            ps.setDouble(7, member.getBalance());

            ps.executeUpdate();
            ps.close();

            ps = con.prepareStatement("INSERT INTO users VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, member.getId());
            ps.setString(2, password);
            ps.setString(3, "APPLIED");

            ps.executeUpdate();
            ps.close();

            updateBalance(member.getId(), 10);
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }
    }//method

    /////////////////////////////////Members////////////////////////////////////
    public ArrayList paymentList(String username) {

        ArrayList paymentList = new ArrayList();
        String query = "SELECT * FROM payments WHERE mem_id ='" + username + "'";

        try {
            selectQuery(query);
            while (resultSet.next()) {
                Payment payment = new Payment();
                payment.setId(resultSet.getInt("id"));
                payment.setMemID(resultSet.getString("mem_id"));
                payment.setTypeOfPayment(resultSet.getString("type_of_payment"));
                payment.setAmount(resultSet.getFloat("amount"));
                payment.setDate(resultSet.getDate("date"));
                payment.setStatus(resultSet.getString("status"));
                paymentList.add(payment);
            }
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }

        return paymentList;
    }//method

    public void makePayment(String username, double amount, String type) {

        PreparedStatement ps = null;
        Date currentDate = new Date(Calendar.getInstance().getTime().getTime());

        try {
            ps = con.prepareStatement("INSERT INTO payments VALUES (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(2, username);
            ps.setString(3, type);
            ps.setDouble(4, amount);
            ps.setDate(5, currentDate);
            ps.setString(6, "SUBMITTED");

            ps.executeUpdate();
            ps.close();

            if (type.equals("MEMBER")) {
                statement = con.createStatement();
                String query = "UPDATE members SET status='APPLIED' WHERE id='" + username + "'";
                statement.executeUpdate(query);
            }
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }
    }//method

    public boolean claimLimit(String username) {

        boolean limit = false;
        int count = 0;
        String query = "SELECT COUNT(*) FROM claims WHERE mem_id = '" + username + "' AND status <> 'REJECTED' AND date BETWEEN '" + startOfYear + "' AND '" + endOfYear + "'";

        try {
            selectQuery(query);
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            if (count >= 2) {
                limit = true;
            }
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }

        return limit;
    }//method

    public void submitClaim(String username, double amount, String rationale) {

        PreparedStatement ps = null;
        Date currentDate = new Date(Calendar.getInstance().getTime().getTime());

        try {
            ps = con.prepareStatement("INSERT INTO claims VALUES (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(2, username);
            ps.setDate(3, currentDate);
            ps.setString(4, rationale);
            ps.setString(5, "SUBMITTED");
            ps.setDouble(6, amount);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }
    }//method

    ////////////////////////////////Admin///////////////////////////////////////
    public ArrayList memberList() {

        ArrayList memberList = new ArrayList();
        String query = "SELECT * FROM members WHERE status NOT LIKE 'ADMIN'";

        try {
            selectQuery(query);
            while (resultSet.next()) {
                Member member = new Member();
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

    public Member getMember(String criteria, String value) {

        Member member = new Member();
        String query = "SELECT * FROM members WHERE " + criteria + " = '" + value + "'";

        try {
            selectQuery(query);
            while (resultSet.next()) {
                member.setId(resultSet.getString("id"));
                member.setName(resultSet.getString("name"));
                member.setAddress(resultSet.getString("address"));
                member.setDob(resultSet.getDate("dob"));
                member.setDor(resultSet.getDate("dor"));
                member.setStatus(resultSet.getString("status"));
                member.setBalance(resultSet.getFloat("balance"));
            }
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }

        return member;
    }//method

    public ArrayList balanceList() {

        ArrayList balanceList = new ArrayList();
        String query = "SELECT * FROM members WHERE id NOT LIKE 'ADMIN' AND balance>0.00";

        try {
            selectQuery(query);
            while (resultSet.next()) {
                Member member = new Member();
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
    
    public ArrayList claimList(String username) {
        
        ArrayList claimList = new ArrayList();        
        if (username.equals("")) {
            username = "%";
        }

        String query = "SELECT * FROM claims WHERE mem_id LIKE'" + username + "'";
        try {
            selectQuery(query);
            while (resultSet.next()) {
                Claim claim = new Claim();

                claim.setAmount(resultSet.getFloat("amount"));
                claim.setDate(resultSet.getDate("date"));
                claim.setId(resultSet.getInt("id"));
                claim.setMemID(resultSet.getString("mem_id"));
                claim.setRationale(resultSet.getString("rationale"));
                claim.setStatus(resultSet.getString("status"));
                claimList.add(claim);
            }
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }
        
        return claimList;
    }//method

    public ArrayList applicationList() {

        ArrayList applicationList = new ArrayList();
        String query = "SELECT * FROM payments WHERE EXISTS(SELECT * FROM members WHERE payments.mem_id = members.id AND members.status='APPLIED') AND type_of_payment='APPLICATION' AND status='SUBMITTED'";

        try {
            selectQuery(query);
            while (resultSet.next()) {
                Payment payment = new Payment();
                payment.setId(resultSet.getInt("id"));
                payment.setMemID(resultSet.getString("mem_id"));
                payment.setTypeOfPayment(resultSet.getString("type_of_payment"));
                payment.setAmount(resultSet.getFloat("amount"));
                payment.setDate(resultSet.getDate("date"));
                payment.setStatus(resultSet.getString("status"));
                applicationList.add(payment);
            }
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }

        return applicationList;
    }//method

    public boolean processClaim(int id, String status) {

        boolean updated = false;
        PreparedStatement ps = null;
        String query = "SELECT id FROM claims WHERE status='SUBMITTED'";
        String update = "UPDATE claims SET status='" + status + "' WHERE id =" + id;

        try {
            selectQuery(query);
            while (resultSet.next() && updated == false) {
                if (id == resultSet.getInt(1)) {
                    ps = con.prepareStatement(update);
                    ps.executeUpdate();
                    ps.close();
                    updated = true;
                }
            }
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }

        return updated;
    }//method

    public boolean processPayment(int id) {

        boolean updated = false;
        String update = "UPDATE payments SET status='APPROVED' WHERE id=" + id;
        String query = "SELECT * FROM payments WHERE status='SUBMITTED'";
        String username;
        String type;
        double amount = 0;
        PreparedStatement ps = null;

        try {
            selectQuery(query);
            while (resultSet.next() && updated == false) {
                if (id == resultSet.getInt("id")) {
                    username = resultSet.getString("mem_id");
                    type = resultSet.getString("tyoe_of_payment");
                    amount = resultSet.getDouble("amount");

                    ps = con.prepareStatement(update);
                    ps.executeUpdate();
                    ps.close();

                    switch (type) {
                        case "MEMBER":
                            updateStatus(username, "APPROVED");
                            break;
                        case "CLAIM":
                            amount = 0 - amount;
                            updateBalance(username, amount);
                            break;
                    }
                    updated = true;
                }
            }
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }

        return updated;
    }//method

    public void claimFee() {

        PreparedStatement ps = null;
        double fee = calcClaimFee();
        String query = "UPDATE members SET balance = (balance+" + fee + ")";

        if (today.equals(endOfYear)) {
            try {
                ps = con.prepareStatement(query);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException s) {
                System.out.println("SQL statement is not executed!");
            }
        }
    }//method

    public double calcTurnover() {
        
        double turnover = 0.0;
        String queryIncome = "SELECT SUM(amount) FROM payments WHERE status='APPROVED' AND date BETWEEN '" + startOfYear + "' AND '" + endOfYear + "'";
        String queryExpense = "SELECT SUM(amount) FROM claims WHERE status='APPROVED' AND date BETWEEN '" + startOfYear + "' AND '" + endOfYear + "'";
        double income = 0.0;
        double expense = 0.0;

        try {
            selectQuery(queryIncome);
            while (resultSet.next()) {
                income = resultSet.getFloat(1);
            }
            selectQuery(queryExpense);
            while (resultSet.next()) {
                expense = resultSet.getFloat(1);
            }
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }
        
        turnover = income - expense;
        
        return turnover;
    }//method
    
    private void suspendMember() {
        
        String query = "SELECT id FROM members WHERE status='APPROVED' AND dor <='" + today + "'";
        
        try {
            selectQuery(query);
            while (resultSet.next()) {
                String username = resultSet.getString(1);
                updateStatus(username, "SUSPENDED");
                updateBalance(username, 10);
            }
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }
    }//method

    private void updateStatus(String username, String status) {
        
        PreparedStatement ps = null;
        String queryApprove = "UPDATE members SET status ='APPROVED', dor =DATE_ADD(dor, INTERVAL 1 YEAR) WHERE id='" + username + "'";
        String querySuspend = "UPDATE members SET status ='SUSPENDED' WHERE id='" + username + "'";
        String queryUser = "UPDATE users SET status ='" + status + "' WHERE id='" + username + "'";
        
        try {
            if (status.equals("APPROVED")) {
                ps = con.prepareStatement(queryApprove);
                ps.executeUpdate();
                ps.close();
            } else {
                ps = con.prepareStatement(querySuspend);
                ps.executeUpdate();
                ps.close();
            }

            ps = con.prepareStatement(queryUser);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }
    }//method

    private void updateBalance(String username, double amount) {
        
        PreparedStatement ps = null;
        String queryUpdate = "UPDATE members SET balance=(balance+" + amount + ") WHERE id='" + username + "'";

        try {
            ps = con.prepareStatement(queryUpdate);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }
    }//method

    private double calcClaimFee() {
        
        double fee = 0;
        double sum = 0;
        double count = 0;
        String queryCount = "SELECT COUNT(*) FROM members";
        String querySum = "SELECT SUM(amount) FROM claims WHERE status ='APPROVED' AND date BETWEEN '" + startOfYear + "' AND '" + endOfYear + "'";

        try {
            selectQuery(queryCount);
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            selectQuery(querySum);
            while (resultSet.next()) {
                sum = resultSet.getFloat(1);
            }
            fee = sum / count;
            fee = (fee * 100d) / 100d;
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }

        return fee;
    }//method

    private void selectQuery(String query) {
        
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }
    }//method
}//class
