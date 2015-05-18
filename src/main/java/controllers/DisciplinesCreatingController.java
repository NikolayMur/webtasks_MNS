package controllers;

import entity.Discipline;
import services.DisciplineServise;
import services.impl.DisceplineServiseImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisciplinesCreatingController extends AbstractWebtasksServletHandler{
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(request.getMethod().equals("GET")){
            request.setAttribute("CURRENT_BUTTON", 1);
            gotoToJSP("/main/discipline/disciplinesCreatingModifying.jsp", request, response);
        }else{
            String strName = request.getParameter("name");
            validateRequestDiscipline(strName);

            DisciplineServise disciplineServise = new DisceplineServiseImpl();
            Boolean resoult = disciplineServise.create(new Discipline(strName));
            if(resoult){
                forwardRequest("/admin/disciplinesList.php",request, response);
            }else{

            }
        }
    }
}
