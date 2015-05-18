package controllers;

import services.StudentService;
import services.impl.StudentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentsDeleteController  extends AbstractWebtasksServletHandler{
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean resoult = false;
        boolean isDigit = true;

        String strId = request.getParameter("ids");
        String[] stringIdArray = strId.split(",");
        int[] idArray = new int[stringIdArray.length];
        for (int i = 0; i < stringIdArray.length; i++) {
            try {
                idArray[i] = Integer.valueOf(stringIdArray[i]);
            } catch (Exception e) {
                isDigit = false;
            }
        }

        if (isDigit){
            StudentService studentServise = new StudentServiceImpl();
            resoult = studentServise.delete(idArray);
        }

        forwardRequest("/admin/studentsList.php",request, response);
    }
}
