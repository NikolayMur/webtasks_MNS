package dao.impl;

import dao.DisciplineDao;
import database.DataService;
import entity.Discipline;

import java.util.List;

public class DisciplineDaoImpl implements DisciplineDao{

    private DataService dataService = new DataService();

    @Override
    public List<Discipline> getDisciplines() {
        return dataService.getAllDisciplines();
    }

    @Override
    public Discipline getById(Discipline discipline) {
        return dataService.getDisciplineById(discipline);
    }

    @Override
    public Discipline update(Discipline discipline) {
        return dataService.updateDiscipline(discipline);
    }

    @Override
    public boolean delete(int id) {
        return dataService.delDisciplineById(id);
    }

    @Override
    public boolean create(Discipline discipline) {
        return dataService.createDiscipline(discipline);
    }
}
