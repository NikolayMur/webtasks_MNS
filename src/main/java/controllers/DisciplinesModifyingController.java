package controllers;

import entity.Discipline;
import services.DisciplineServise;
import services.impl.DisceplineServiseImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisciplinesModifyingController extends AbstractWebtasksServletHandler{
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(request.getMethod().equals("GET")){
            request.setAttribute("CURRENT_BUTTON", 2);
            boolean isDigit = true;
            String strId = request.getParameter("id");
            int id = 0;
            try {
                id = Integer.valueOf(strId);
            } catch (Exception e) {
                isDigit = false;
            }
            if (isDigit){
                Discipline discipline = new Discipline();
                discipline.setId(id);

                DisciplineServise disciplineServise = new DisceplineServiseImpl();

                discipline = disciplineServise.getById(discipline);
                request.setAttribute("discipline", discipline);
            }
            gotoToJSP("/main/discipline/disciplinesCreatingModifying.jsp", request,response);
        }else{

            Discipline discipline = new Discipline();
            boolean isDigit = true;
            String strName = request.getParameter("name");
            validateRequestDiscipline(strName);

            String strId = request.getParameter("id");
            int id = 0;
            try {
                id = Integer.valueOf(strId);
            } catch (Exception e) {
                isDigit = false;
            }
            if (isDigit){
                discipline = new Discipline(id, strName);

                DisciplineServise disciplineServise = new DisceplineServiseImpl();

                discipline = disciplineServise.update(discipline);
                request.setAttribute("discipline", discipline);
            }

            request.setAttribute("discipline", discipline);
            gotoToJSP("/main/discipline/disciplinesCreatingModifying.jsp", request,response);

        }
    }
}
