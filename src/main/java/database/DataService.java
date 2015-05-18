package database;

import constants.Constants;
import entity.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DataService{
	private static final Logger LOGGER = Logger
			.getLogger(DataService.class);
	private static List<DBConnection> conPool = new ArrayList<DBConnection>();
	private static Object monitor = new Object();

	public boolean init() {
		try{
			LOGGER.info("init database");
			for (int i = 0; i < Constants.CONNECTING_POOL_SIZE; i++){
				newConnection();
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public DBConnection getDBConnection() {
		synchronized (monitor) {
			if (conPool.isEmpty()){
				newConnection();
			}
			DBConnection conn = conPool.remove(0);
			return conn;
		}
	}
	
	public void putDBConnection(DBConnection conn) {
		synchronized (monitor){
			conPool.add(conn);
		}
	}

	private void newConnection() {
		DBConnection conn = new DBConnection(Constants.CONNECTING_URL, Constants.CONNECTING_USER, Constants.CONNECTING_PASSWORD);
		synchronized (monitor) {
			conPool.add(conn);
		}
	}
	
	public List<Role> getAllRoles(){
		DBConnection conn = getDBConnection();
		List <Role> result = conn.getAllRoles();
		this.putDBConnection(conn);
		return result;
		
	}
	
	public List<Account> getAllLogins() {
		DBConnection conn = getDBConnection();
		List<Account> result = conn.getAllLogins();
		this.putDBConnection(conn);		
		return result;
	}
	
	public Account getAccountByLogin(String login){
		DBConnection conn = getDBConnection();
		Account result = conn.getAccountByLogin(login);
		this.putDBConnection(conn);
		return result;
	}
	
	public List<Role> getRolesById(int id){
		DBConnection conn = getDBConnection();
		List<Role> result = conn.getRolesById(id);
		this.putDBConnection(conn);
		return result;
		
	}
	
	public List<Integer> getIdAccountRoles(int idAccount){
		DBConnection conn = getDBConnection();
		List<Integer> idAccountRoles = conn.getIdAccountRoles(idAccount);
		this.putDBConnection(conn);
		return idAccountRoles;
	}

	//Disciplines
	public List<Discipline> getAllDisciplines(){
		DBConnection conn = getDBConnection();
		List<Discipline> disciplineList = conn.getAllDisciplines();
		this.putDBConnection(conn);
		return disciplineList;
	}

	public boolean delDisciplineById(int idDiscipline) {
		DBConnection conn = getDBConnection();
		boolean resoult = conn.delDisciplineById(idDiscipline);
		this.putDBConnection(conn);
		return resoult;
	}

	public boolean createDiscipline(Discipline discipline) {
		DBConnection conn = getDBConnection();
		boolean resoult = conn.createDiscipline(discipline);
		this.putDBConnection(conn);
		return resoult;
	}

	public Discipline getDisciplineById(Discipline discipline) {
		DBConnection conn = getDBConnection();
		Discipline resoult = conn.getDisciplineById(discipline);
		this.putDBConnection(conn);
		return resoult;
	}

	public Discipline updateDiscipline(Discipline discipline) {
		DBConnection conn = getDBConnection();
		discipline = conn.updateDiscipline(discipline);
		this.putDBConnection(conn);
		return discipline;
	}

	//Students
	public List<Student> getAllStudents(){
		DBConnection conn = getDBConnection();
		List<Student> studentList = conn.getAllStudents();
		this.putDBConnection(conn);
		return studentList;
	}

	public boolean createStudent(Student student) {
		DBConnection conn = getDBConnection();
		boolean resoult = conn.createStudent(student);
		this.putDBConnection(conn);
		return resoult;
	}

	public Student getStudentById(Student student) {
		DBConnection conn = getDBConnection();
		Student resoult = conn.getStudentById(student);
		this.putDBConnection(conn);
		return resoult;
	}

	public Student updateStudent(Student student) {
		DBConnection conn = getDBConnection();
		student = conn.updateStudent(student);
		this.putDBConnection(conn);
		return student;
	}

	public boolean delStudentsById(int[] idArray) {
		DBConnection conn = getDBConnection();
		boolean resoult = conn.delStudentsById(idArray);
		this.putDBConnection(conn);
		return resoult;
	}


	//Terms
	public List<Term> getAllTerms(){
		DBConnection conn = getDBConnection();
		List<Term> termList = conn.getAllTerms();
		this.putDBConnection(conn);
		return termList;
	}

	public List<Discipline> getDisciplinesByIdTerm(int idChooser){
		DBConnection conn = getDBConnection();
		List<Discipline> disciplinesList = conn.getDisciplinesByIdTerm(idChooser);
		this.putDBConnection(conn);
		return disciplinesList;
	}

	public boolean delTermById(int idTerm) {
		DBConnection conn = getDBConnection();
		boolean resoult = conn.delTermById(idTerm);
		this.putDBConnection(conn);
		return resoult;
	}

	public boolean createTerm(Term term) {
		DBConnection conn = getDBConnection();
		boolean resoult = conn.createTerm(term);
		this.putDBConnection(conn);
		return resoult;
	}

	public Term updateTerm(Term term) {
		DBConnection conn = getDBConnection();
		term = conn.updateTerm(term);
		this.putDBConnection(conn);
		return term;
	}

	public Term getTermById(Term term) {
		DBConnection conn = getDBConnection();
		Term resoult = conn.getTermById(term);
		this.putDBConnection(conn);
		return resoult;
	}

	public void close() {
		
	}	
}
