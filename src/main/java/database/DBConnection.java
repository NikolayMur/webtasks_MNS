package database;

import entity.*;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class DBConnection {
    private static final Logger LOGGER = Logger.getLogger(DBConnection.class);
    private Connection conn = null;
    private ResultSet rs = null;

    private static PreparedStatement loadAllRoles;
    private static PreparedStatement loadAllLogins;
    private static PreparedStatement loadAccountByLogin;
    private static PreparedStatement loadRolesById;
    private static PreparedStatement loadRolesByAccountId;
    private static PreparedStatement getIdAccountRoles;

    //DisciplinesList
    private static PreparedStatement getDisciplinesList;
    private static PreparedStatement delDisciplineById;
    private static PreparedStatement createDiscipline;
    private static PreparedStatement getDisciplineById;
    private static PreparedStatement updateDisciplineById;
    //Students
    private static PreparedStatement getStudentsList;
    private static PreparedStatement createStudent;
    private static PreparedStatement getStudentById;
    private static PreparedStatement updateStudentById;
    private static PreparedStatement delStudentsById;
    //Terms
    private static PreparedStatement getTermsList;
    private static PreparedStatement getDisciplinesByIdTerm;
    private static PreparedStatement delTermById;
    private static PreparedStatement createTerm;
    private static PreparedStatement createTerm_Disciplines;
    private static PreparedStatement delTerm_Disciplines;
    private static PreparedStatement updateTermById;
    private static PreparedStatement getTermById;


    public DBConnection(String url, String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver"); //- error: sqlArray = conn.createArrayOf("bigint", strIdArray);


            conn = DriverManager.getConnection(url, user, password);
            loadPreparedStatements();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            LOGGER.error("ClassNotFoundException - > DBConnection(String url); " + e);

        }
    }

    private void loadPreparedStatements() {
        try {
            //Account and Roles
            loadAllRoles = conn.prepareStatement("SELECT * FROM roles");
            loadAllLogins = conn.prepareStatement("SELECT login, id FROM account");
            loadAccountByLogin = conn.prepareStatement("SELECT * FROM account WHERE login = ?");
            loadRolesByAccountId = conn.prepareStatement("SELECT * FROM account_role WHERE id_account =?");
            loadRolesById = conn.prepareStatement("SELECT * FROM roles WHERE id_roles =?");
            getIdAccountRoles = conn.prepareStatement("SELECT id_role FROM account_role WHERE id_account = ?");
            //Disciplines
            getDisciplinesList = conn.prepareStatement("SELECT * FROM disciplines");
            delDisciplineById = conn.prepareStatement("DELETE FROM disciplines WHERE id_discipline = ?");
            createDiscipline = conn.prepareStatement("INSERT disciplines (name) VALUES (?)");
            getDisciplineById = conn.prepareStatement("SELECT * FROM disciplines WHERE id_discipline = ?");
            updateDisciplineById = conn.prepareStatement("UPDATE disciplines SET name=? WHERE id_discipline=?");
            //Students
            getStudentsList = conn.prepareStatement("SELECT * FROM students");
            createStudent = conn.prepareStatement("INSERT students (name, last_name, date, groupe) VALUES (?, ?, ?, ?)");
            getStudentById = conn.prepareStatement("SELECT * FROM students WHERE id_student = ?");
            updateStudentById = conn.prepareStatement("UPDATE students SET name = ?, last_name = ?, date = ?, groupe = ? WHERE id_student = ?");
            delStudentsById = conn.prepareStatement("");
            //Terms
            getTermsList = conn.prepareStatement("SELECT * FROM terms");
            getDisciplinesByIdTerm = conn.prepareStatement("SELECT * FROM disciplines WHERE id_discipline IN (SELECT disciplines_of_semesters.id_discipline FROM disciplines_of_semesters WHERE id_semester = ?)");
            delTermById = conn.prepareStatement("DELETE FROM terms WHERE id_term = ?");
            createTerm = conn.prepareStatement("INSERT terms (duration) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            createTerm_Disciplines = conn.prepareStatement("INSERT disciplines_of_semesters (id_semester, id_discipline) VALUES (?, ?)");
            delTerm_Disciplines = conn.prepareStatement("DELETE FROM disciplines_of_semesters WHERE id_semester = ?");
            updateTermById = conn.prepareStatement("UPDATE terms SET duration = ? WHERE id_term = ?");
            getTermById = conn.prepareStatement("SELECT * FROM terms WHERE id_term = ?");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void closePreparedStatements() {
        try {
            loadAllRoles.close();
            loadAllLogins.close();
            loadAccountByLogin.close();
            loadRolesById.close();
            loadRolesByAccountId.close();
            getIdAccountRoles.close();

            getDisciplinesList.close();
            delDisciplineById.close();
            createDiscipline.close();
            getDisciplineById.close();
            updateDisciplineById.close();

            getStudentsList.close();
            createStudent.close();
            getStudentById.close();
            updateStudentById.close();
            delStudentsById.close();

            getTermsList.close();
            getDisciplinesByIdTerm.close();
            delTermById.close();
            createTerm.close();
            createTerm_Disciplines.close();
            delTerm_Disciplines.close();
            updateTermById.close();
            getTermById.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Role> getAllRoles() {
        //loadAllRoles = conn.prepareStatement("SELECT * FROM roles");
        rs = null;
        List<Role> result = new LinkedList<Role>();
        try {
            rs = loadAllRoles.executeQuery();
            while (rs.next()) {
                Role r = new Role();
                r.setId(rs.getInt("id_roles"));
                r.setName(rs.getString("name_roles"));
                result.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    public List<Account> getAllLogins() {
        //loadAllLogins = conn.prepareStatement("SELECT login, id FROM account");
        rs = null;
        List<Account> result = new LinkedList<Account>();
        try {
            rs = loadAllLogins.executeQuery();

            while (rs.next()) {
                Account account = new Account();
                account.setUsername(rs.getString("login"));
                account.setId(rs.getInt("id"));
                //account.setPassword(rs.getString("password"));
                result.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public Account getAccountByLogin(String login) {
        rs = null;
        // loadAccountByLogin = conn.prepareStatement("SELECT * FROM account WHERE login = ?");
        Account result = new Account();
        try {
            loadAccountByLogin.setNString(1, login);
            rs = loadAccountByLogin.executeQuery();

            while (rs.next()) {
                //result.setId(rs.getInt("id"));
                result.setId(rs.getInt("id_account"));
                result.setUsername(rs.getString("login"));
                result.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public List<Role> getRolesById(int id) {
        //loadRolesById = conn.prepareStatement("SELECT * FROM roles WHERE id_roles =?");
        rs = null;
        List<Role> result = new LinkedList<Role>();
        try {
            loadRolesById.setInt(1, id);
            rs = loadRolesById.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id_roles"));
                role.setName(rs.getString("name_roles"));
                result.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public void close() {
        closePreparedStatements();
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            LOGGER.info("close() exeption " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Integer> getIdAccountRoles(int idAccount) {
        //getIdAccountRoles = conn.prepareStatement("SELECT id_role FROM account_role WHERE id_account = ?");
        rs = null;
        List<Integer> idAccountRoles = new ArrayList<Integer>();
        try {
            getIdAccountRoles.setInt(1, idAccount);
            rs = getIdAccountRoles.executeQuery();

            while (rs.next()) {
                idAccountRoles.add(rs.getInt("id_role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idAccountRoles;
    }

    //Disciplines

    public List<Discipline> getAllDisciplines() {
        //getDisciplinesList = conn.prepareStatement("SELECT * FROM disciplines");
        rs = null;
        List<Discipline> result = new LinkedList<Discipline>();
        try {
            rs = getDisciplinesList.executeQuery();
            while (rs.next()) {
                Discipline r = new Discipline();
                r.setId(rs.getInt("id_discipline"));
                r.setName(rs.getString("name"));
                result.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    public boolean delDisciplineById(int idDiscipline) {
        //delDisciplineById = conn.prepareStatement("DELETE FROM disciplines WHERE id_discipline = ?");
        boolean result = false;
        rs = null;
        try {
            delDisciplineById.setInt(1, idDiscipline);
            result = delDisciplineById.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean createDiscipline(Discipline discipline) {
        //createDiscipline = conn.prepareStatement("INSERT disciplines (name) VALUES (?)");
        int result = 0;
        rs = null;
        try {
            createDiscipline.setString(1, discipline.getName());
            result = createDiscipline.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(result > 0){
            return true;
        }else{
            return false;}
    }

    public Discipline getDisciplineById(Discipline discipline) {
        //getDisciplineById = conn.prepareStatement("SELECT * FROM disciplines WHERE id_discipline = ?");
        ResultSet resultSets = null;
        rs = null;
        try {
            getDisciplineById.setInt(1, discipline.getId());
            resultSets = getDisciplineById.executeQuery();
            while (resultSets.next()) {
                discipline.setName(resultSets.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return discipline;
    }

    public Discipline updateDiscipline(Discipline discipline) {
        //updateDisciplineById = conn.prepareStatement("UPDATE disciplines SET name=? WHERE id_discipline=?");
        int result = 0;
        rs = null;
        try {
            updateDisciplineById.setString(1, discipline.getName());
            updateDisciplineById.setInt(2, discipline.getId());
            result = updateDisciplineById.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(result > 0){
            return discipline;
        }else{
            discipline.setName("");
            return discipline;}
    }

    //Students
    public List<Student> getAllStudents() {
        //getStudentsList = conn.prepareStatement("SELECT * FROM students");
        rs = null;
        List<Student> result = new LinkedList<Student>();
        try {
            rs = getStudentsList.executeQuery();
            while (rs.next()) {
                Student r = new Student();
                r.setId(rs.getInt("id_student"));
                r.setName(rs.getString("name"));

                r.setLastName(rs.getString("last_name"));
                r.setDate(rs.getDate("date"));
                r.setGroupe(rs.getString("groupe"));
                result.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    public boolean createStudent(Student student) {
        //createStudent = conn.prepareStatement("INSERT students (name, last_name, date, groupe) VALUES (?, ?, ?, ?)");
        int result = 0;
        rs = null;
        try {
            createStudent.setString(1, student.getName());

            createStudent.setString(2, student.getLastName());

            java.sql.Date sqlDate = new java.sql.Date(student.getDate().getTime());
            createStudent.setDate(3, sqlDate);

            createStudent.setString(4, student.getGroupe());

            result = createStudent.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(result > 0){
            return true;
        }else{
            return false;}
    }

    public Student getStudentById(Student student) {
        //getStudentById = conn.prepareStatement("SELECT * FROM students WHERE id_student = ?");
        ResultSet resultSets = null;
        rs = null;
        try {
            getStudentById.setInt(1, student.getId());
            resultSets = getStudentById.executeQuery();
            while (resultSets.next()) {
                student.setName(resultSets.getString("name"));
                student.setLastName(resultSets.getString("last_name"));

                student.setDate(new java.util.Date(resultSets.getDate("date").getTime()));
                student.setGroupe(resultSets.getString("groupe"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    public Student updateStudent(Student student) {
        //updateStudentById = conn.prepareStatement("UPDATE students SET name = ?, last_name = ?, date = ?, groupe = ? WHERE id_student = ?");
        int result = 0;
        rs = null;
        try {
            updateStudentById.setString(1, student.getName());
            updateStudentById.setString(2, student.getLastName());

            java.sql.Date sqlDate = new java.sql.Date(student.getDate().getTime());
            updateStudentById.setDate(3, sqlDate);

            updateStudentById.setString(4, student.getGroupe());

            updateStudentById.setInt(5, student.getId());
            result = updateStudentById.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result > 0) {
            return student;
        } else {
            student.setName("");
            student.setLastName("");
            student.setGroupe("");
            return student;
        }
    }

    public boolean delStudentsById(int[] idArray) {
//       delStudentsById = conn.prepareStatement("");
        boolean result = false;
        rs = null;
        if (idArray.length == 0) {
            return result;
        }

        String str = " id_student = " + String.valueOf(idArray[0]);
        for (int i = 1; i < idArray.length; i++) {
            str = str + " OR id_student = " + String.valueOf(idArray[i]);
        }

        try {
            delStudentsById = conn.prepareStatement("DELETE FROM students WHERE " + str);
            result = delStudentsById.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    //Terms
    public List<Term> getAllTerms() {
        //getTermsList = conn.prepareStatement("SELECT * FROM terms");
        rs = null;
        List<Term> result = new LinkedList<Term>();
        try {
            rs = getTermsList.executeQuery();
            while (rs.next()) {
                Term r = new Term();
                r.setId(rs.getInt("id_term"));
                r.setYear(rs.getInt("year"));
                r.setNumber(rs.getInt("number"));
                r.setDuration(rs.getInt("duration"));

                result.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Discipline> getDisciplinesByIdTerm(int idChooser){
        //getDisciplinesByIdTerm = conn.prepareStatement("SELECT * FROM disciplines WHERE id_discipline IN (SELECT disciplines_of_semesters.id_discipline FROM disciplines_of_semesters WHERE id_semester = ?)");
        rs = null;
        List<Discipline> result = new LinkedList<Discipline>();
        try {
            getDisciplinesByIdTerm.setInt(1, idChooser);
            rs = getDisciplinesByIdTerm.executeQuery();
            while (rs.next()) {
                Discipline r = new Discipline();
                r.setId(rs.getInt("id_discipline"));
                r.setName(rs.getString("name"));
                result.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean delTermById(int idTerm) {
        //delTermById = conn.prepareStatement("DELETE FROM terms WHERE id_term = ?");
        boolean result = false;
        rs = null;
        try {
            delTermById.setInt(1, idTerm);
            result = delTermById.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean createTerm(Term term) {
        //createTerm = conn.prepareStatement("INSERT terms (duration) VALUES (?)");
        int result = 0;
        ResultSet rs = null;
        int idTerm = 0;
        try {
            createTerm.setInt(1, term.getDuration());
            result = createTerm.executeUpdate();
            rs = createTerm.getGeneratedKeys();
            if (rs.next()){
                idTerm = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        if(result > 0 && idTerm > 0){
            //createTerm_Disciplines = conn.prepareStatement("INSERT disciplines_of_semesters (id_semester, id_discipline) VALUES (?, ?)");
            try {
            createTerm_Disciplines.setInt(1, idTerm);
            for(Discipline discipline: term.getDiscipline()){
                createTerm_Disciplines.setInt(2, discipline.getId());
                result = createTerm_Disciplines.executeUpdate();
                if(result <= 0){
                    return false;
                }
            }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return true;
        }else{
            return false;}
    }

    public Term updateTerm(Term term) {
        //delTerm_Disciplines = conn.prepareStatement("DELETE FROM disciplines_of_semesters WHERE id_term = ?");
        //updateTermById = conn.prepareStatement("UPDATE terms SET duration = ? WHERE id_term = ?");
        int result_delTerm_Disciplines = 0;
        int result_updateTermById = 0;
        int result_createTerm_Disciplines = 0;
        rs = null;
        try {
            delTerm_Disciplines.setInt(1, term.getId());
            result_delTerm_Disciplines = delTerm_Disciplines.executeUpdate();

            updateTermById.setInt(1, term.getDuration());
            updateTermById.setInt(2, term.getId());
            result_updateTermById = updateTermById.executeUpdate();

            createTerm_Disciplines.setInt(1, term.getId());
            for(Discipline discipline: term.getDiscipline()){
                createTerm_Disciplines.setInt(2, discipline.getId());
                result_createTerm_Disciplines = createTerm_Disciplines.executeUpdate();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result_delTerm_Disciplines > 0 && result_updateTermById > 0 && result_createTerm_Disciplines > 0) {
            return term;
        } else {
            term.setDiscipline(new LinkedList<Discipline>());
            term.setDuration(0);
            return term;
        }
    }

    public Term getTermById(Term term) {
        //getTermById = conn.prepareStatement("SELECT * FROM terms WHERE id_term = ?");
        ResultSet resultSets = null;
        rs = null;
        try {
            getTermById.setInt(1, term.getId());
            resultSets = getTermById.executeQuery();
            while (resultSets.next()) {
                term.setDuration(resultSets.getInt("duration"));
                term.setNumber(resultSets.getInt("number"));
                term.setYear(resultSets.getInt("year"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return term;
    }

}
