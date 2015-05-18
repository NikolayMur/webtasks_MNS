package dao;

import entity.Discipline;

import java.util.List;

public interface DisciplineDao {

	List<Discipline> getDisciplines();

	Discipline getById(Discipline discipline);

	Discipline update(Discipline discipline);

	boolean delete(int id);

	boolean create(Discipline discipline);

}
