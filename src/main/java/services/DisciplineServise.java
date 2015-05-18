package services;

import entity.Discipline;

import java.util.List;

public interface DisciplineServise {

	Discipline update(Discipline discipline);

	boolean delete(int id);

	boolean create(Discipline discipline);

	List<Discipline> getDisciplines();

	Discipline getById(Discipline discipline);

}
