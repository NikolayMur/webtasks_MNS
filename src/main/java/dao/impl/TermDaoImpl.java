package dao.impl;

import dao.TermDao;
import database.DataService;
import entity.Discipline;
import entity.Term;

import java.util.List;

public class TermDaoImpl implements TermDao {

    private DataService dataService = new DataService();

    @Override
    public List<Term> getTerms() {
        return dataService.getAllTerms();
    }

    @Override
    public Term getById(Term term) {
        return dataService.getTermById(term);
    }

    @Override
    public Term update(Term term) {
        return dataService.updateTerm(term);
    }

    @Override
    public boolean delete(int id) {
        return dataService.delTermById(id);
    }

    @Override
    public boolean create(Term term) {
        return dataService.createTerm(term);
    }

    public List<Discipline> getDisciplinesByIdTerm(int idChooser){
        return dataService.getDisciplinesByIdTerm(idChooser);
    }
}
