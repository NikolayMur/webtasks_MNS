package controllers;

import entity.Discipline;
import services.DisciplineServise;
import services.impl.DisceplineServiseImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DisciplinesListController extends AbstractWebtasksServletHandler{
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DisciplineServise disciplineServise = new DisceplineServiseImpl();
        List<Discipline> disciplineList = disciplineServise.getDisciplines();

        request.setAttribute("disciplines", disciplineList);
        gotoToJSP("/main/discipline/disciplinesList.jsp",request,response);

    }
}
