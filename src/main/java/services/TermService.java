package services;

import entity.Discipline;
import entity.Term;

import java.util.List;

public interface TermService {
	Term update(Term term);

	boolean delete(int id);

	boolean create(Term term);

	List<Term> getTerms();

	public List<Discipline> getDisciplinesByIdTerm(int idChooser);

	Term getById(Term term);
}
