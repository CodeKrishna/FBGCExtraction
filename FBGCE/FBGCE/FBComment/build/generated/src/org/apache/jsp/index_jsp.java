package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.json.JsonObject;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!--The following are the imports of java packages -->\n");
      out.write("\n");
      out.write("\n");
      out.write("<!--The following are the imports of facebook API for java called restfb -->\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Facebook Group Post Comment Extraction </title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1><center>Facebook Group Post Comment Extraction OUTPUT</center></h1>\n");
      out.write("        <br>\n");
      out.write("\n");
      out.write("\n");
      out.write("        <!-- JSP code-->\n");
      out.write("        ");

            //Creation of a FacebookClient object and passing the facebook Graph API acess token
            //this token expires after 2 hours so must be changed in the code as well.
            //However Once we create a facebook app we will get a app id and app secret which does not change 
            //So we can create a FacebookClient Object that takes app id and app secret so that we don't need to change it every 2 hours
            FacebookClient fbClient = new DefaultFacebookClient("CAACEdEose0cBAE5aJlRP5278wOgqvnvEOgSqTwSbgQqMui7zTbkBrcwkovOI9tYyQWLpGH9d4zj7WdwnIOuD1wQfvBtEzfgK0nGTAMa3yWJWaOxiWTZBYLLiZCCOg5EwKSUZBDZCIZBJoM0inBSc0X3HFo8ZBrqi2YXJRPG4xNvSFEamC1RcWoRvPzCW63IZCua9JUTimAZBTUeBWM27Abt1a5WlDCSHASQZD", Version.UNVERSIONED);
            int p = 0;

            //JsonObject created to fetch the object whose id is 867927643271221 i.e the Group from which we want data
            //867927643271221/feed refers to the group posts
            JsonObject GroupPostConnection = fbClient.fetchObject("867927643271221/feed", JsonObject.class);
            //Extracting the Json output from the part where first occurce of the word "data" is present to the end of output.
            //This output resut is stored in the form of a string.
            //getString(0) refers to the 1st post in the group
            String UnParsedText = GroupPostConnection.getJsonArray("data").getString(0);

            //Below is the logic for parsing of the string to get rid of unwanted string and retaining only the messages.
            //and storing each message in an arraylist
            String s1 = UnParsedText;
            s1 = s1.substring(s1.indexOf("\"comments\""));
            ArrayList<String> arr = new ArrayList<String>();

            while (s1.indexOf("\"message\"") != -1)
            {
                p = s1.indexOf("\"message\"");
                arr.add(s1.substring(p, s1.indexOf(",", p)));
                s1 = s1.substring(p + 1);
            }

            //little more parsing for proper display of the string output messages stored in the array list and displaying them   
            for (String x : arr.toArray(new String[arr.size()]))
            {
                x = x.replaceAll("\"", "");
                x = x.replaceAll(":", ":- ");
                out.println(x);
                out.println("<br><br>");
            }
        
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
