package services.impl;

import dao.TermDao;
import dao.impl.TermDaoImpl;
import entity.Discipline;
import entity.Term;
import services.TermService;

import java.util.List;

public class TermServiceImpl  implements TermService {

    private TermDao termDao = new TermDaoImpl();

    @Override
    public Term update(Term term) {
        return termDao.update(term);
    }

    @Override
    public boolean delete(int id) {
        return termDao.delete(id);
    }

    @Override
    public boolean create(Term term) {
        return termDao.create(term);
    }

    @Override
    public List<Term> getTerms() {
        return termDao.getTerms();
    }

    @Override
    public Term getById(Term term) {
        return termDao.getById(term);
    }

    public List<Discipline> getDisciplinesByIdTerm(int idChooser){
        return termDao.getDisciplinesByIdTerm(idChooser);
    }
}
