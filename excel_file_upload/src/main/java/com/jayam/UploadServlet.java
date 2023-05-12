package com.jayam;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.dao.FileUploadDao;

@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 30177215) // upload file's size up to 30MB
public class UploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1 ;

    private FileUploadDao fileUploadDao;

    @Override
    public void init() {
        fileUploadDao = new FileUploadDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // gets values of text fields
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        InputStream inputStream = null; // input stream of the upload file

        String message =null;
       
        Part filePart = request.getPart("photo");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }

        // sends the statement to the database server
        int row = fileUploadDao.uploadFile(firstName, lastName, inputStream);
        if (row > 0) {
            message = "File uploaded and saved into database";
        }
        else {
			System.out.println("FILE NOT UPLOADED SUCCESFULLY");
		}

        // sets the message in request scope
        request.setAttribute("Message", message);

        // forwards to the message page
        getServletContext().getRequestDispatcher("/message.jsp")
            .forward(request, response);
    }
}

