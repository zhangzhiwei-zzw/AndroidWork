import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UploadFileServlet",urlPatterns = "/UploadFileServlet")
@MultipartConfig
public class UploadFileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public UploadFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *   response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *   response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doPost==");
        request.setCharacterEncoding("utf-8");
        //获取file命名的part，注意要与Android端一样
        Part part = request.getPart("file");
        // 获取请求头，请求头的格式：form-data; name="file"; filename="snmp4j--api.zip"
        String header = part.getHeader("content-disposition");
        System.out.println(header);
        String fileName = getFileName(header);
        // 存储路径
        String savePath = "D:/huang/upload";
        // 把文件写到指定路径
        part.write(savePath + File.separator + fileName);


        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print("上传成功");
    }



    public String getFileName(String header) {
        String[] tempArr1 = header.split(";");
        String[] tempArr2 = tempArr1[2].split("=");
        // 获取文件名，兼容各种浏览器的写法
        String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
        return fileName;
    }

}