package controllers;

import services.TermService;
import services.impl.TermServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TermDeleteController  extends AbstractWebtasksServletHandler{
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
            TermService termService = new TermServiceImpl();
            resoult = termService.delete(id);
        }

        forwardRequest("/admin/termsList.php",request, response);
    }
}
