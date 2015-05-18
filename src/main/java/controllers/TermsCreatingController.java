package controllers;

import entity.Discipline;
import entity.Term;
import services.DisciplineServise;
import services.TermService;
import services.impl.DisceplineServiseImpl;
import services.impl.TermServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TermsCreatingController extends AbstractWebtasksServletHandler {
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {

            request.setAttribute("CURRENT_BUTTON", 1);

            DisciplineServise disciplineServise = new DisceplineServiseImpl();
            List<Discipline> disciplineList = disciplineServise.getDisciplines();

            request.setAttribute("disciplines", disciplineList);

            gotoToJSP("/main/term/termCreatingModifying.jsp", request, response);

        } else {

            Map<String, String[]> map = request.getParameterMap();
            String[] strIdDisciplines = map.get("multi_list");
            String strDuration = map.get("duration")[0];

            validateRequestTerm(strDuration, strIdDisciplines);

            List<Discipline> disciplineList = new LinkedList<Discipline>();
            for (int i = 0; i < strIdDisciplines.length; i++) {
                disciplineList.add(new Discipline(Integer.valueOf(strIdDisciplines[i]), ""));
            }

            TermService termService = new TermServiceImpl();
            Boolean resoult = termService.create(new Term(0, Integer.valueOf(strDuration), disciplineList));

            if (resoult) {
                forwardRequest("/admin/termsList.php", request, response);
            } else {

            }

        }
    }
}
