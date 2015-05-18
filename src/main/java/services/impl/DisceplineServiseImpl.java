package services.impl;

import dao.DisciplineDao;
import dao.impl.DisciplineDaoImpl;
import entity.Discipline;
import services.DisciplineServise;

import java.util.List;

public class DisceplineServiseImpl implements DisciplineServise{

    private DisciplineDao disciplineDao = new DisciplineDaoImpl();
    @Override
    public Discipline update(Discipline discipline) {
        return disciplineDao.update(discipline);
    }

    @Override
    public boolean delete(int id) {
        return disciplineDao.delete(id);
    }

    @Override
    public boolean create(Discipline discipline) {
        return disciplineDao.create(discipline);
    }



    @Override
    public List<Discipline> getDisciplines() {
        //logika proverki na "null", variant predostavleniya informacii
        return disciplineDao.getDisciplines();
    }

    @Override
    public Discipline getById(Discipline discipline) {
        return disciplineDao.getById(discipline);
    }
}
