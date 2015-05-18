package controllers;

import services.DisciplineServise;
import services.impl.DisceplineServiseImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisciplineDeleteController extends AbstractWebtasksServletHandler{
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        boolean resoult = false;
        boolean isDigit = true;
        String strId = request.getParameter("id");
        int id = 0;

        try {
            id = Integer.valueOf(strId);
        } catch (Exception e) {
            isDigit = false;
        }
        if (isDigit){
            DisciplineServise disciplineServise = new DisceplineServiseImpl();
            resoult = disciplineServise.delete(id);
        }

        forwardRequest("/admin/disciplinesList.php",request, response);
    }
}
