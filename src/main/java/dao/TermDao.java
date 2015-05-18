package dao;

import entity.Discipline;
import entity.Term;

import java.util.List;

public interface TermDao {

    List<Term> getTerms();

    public List<Discipline> getDisciplinesByIdTerm(int idChooser);

    Term getById(Term term);

    Term update(Term term);

    boolean delete(int id);

    boolean create(Term term);
}
