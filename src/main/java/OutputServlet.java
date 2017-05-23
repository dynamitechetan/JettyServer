import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by chetankaushik on 24/05/17.
 */
public class OutputServlet extends HttpServlet {

    public void requests(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException, IOException {
        resp.setContentType(req.getParameter("type") != null ? req.getParameter("type") : "text/plain");
        if(req.getParameter("echo") != null){
            resp.getWriter().print(req.getParameter("echo"));
        } else {
            resp.getWriter().println("Url Getter Server");
            resp.getWriter().println("Path: "+req.getRequestURI());
            resp.getWriter().print("\nHeaders:\n"+getHeaderString(req));
            resp.getWriter().print("\nParameters:\n"+getdataString(req.getParameterMap()));
        }
        resp.setStatus( HttpServletResponse.SC_OK );
    }


    public static String getHeaderString(HttpServletRequest req) {
        String headers = "";
        req.getHeaderNames();
        for (Enumeration e = req.getHeaderNames(); e.hasMoreElements() ;) {
            String element = (String) e.nextElement();
            String value = req.getHeader(element);
            headers = headers + element + ": " + value + "\n";
        }
        return headers;
    }

    public static String getdataString(Map<String, String[]> map) throws UnsupportedEncodingException {
        String data = "";
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            for(int i=0;i<values.length;i++){
                data = data + name + ": " + values[i] +"\n";
            }
        }
        return data;
    }
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        requests(req,resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        requests(req,resp);
    }

    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        requests(req,resp);
    }
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        requests(req,resp);
    }
}
