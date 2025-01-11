/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
@WebServlet(name = "FileUploadController", urlPatterns = {"/FileUploadController"})
public class FileUploadController extends HttpServlet {

    private static final long serialVersionUID = 1;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

@Override
public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String file_name = null;
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
    if (!isMultipartContent) {
        return;
    }
    FileItemFactory factory = new DiskFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload(factory);
    try {
        List<FileItem> fields = upload.parseRequest(request);
        Iterator<FileItem> it = fields.iterator();
        if (!it.hasNext()) {
            return;
        }
        while (it.hasNext()) {
            FileItem fileItem = it.next();
            boolean isFormField = fileItem.isFormField();
            if (isFormField) {
                if (file_name == null) {
                    if (fileItem.getFieldName().equals("file_name")) {
                        file_name = fileItem.getString();
                    }
                }
            } else {
                if (fileItem.getSize() > 0) {
                    String fileName = file_name + ".jpg"; // Set the file name with extension
                    File uploadDir = new File("C:\\Users\\Dell\\Desktop\\SWP391_SMK(offline)\\web\\images\\product\\");
                    File existingFile = new File(uploadDir, fileName);
                    if (existingFile.exists()) {
                        existingFile.delete();
                    }
                    fileItem.write(new File(uploadDir, fileName));
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        out.println("<script type='text/javascript'>");
        out.println("window.location.href='item/view.do'");
        out.println("</script>");
        out.close();
    }
}


}
