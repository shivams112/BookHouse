package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class Validate_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"errorpage.jsp", true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<html>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");

	Connection conn;
	conn=null;
	ResultSet rs;
	rs=null;
	try
	{
		String strName=request.getParameter("UserName");
		String strPass=request.getParameter("Password");
		Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/bookhouse";
                String username = "root";
                String password = "1234";
                conn = DriverManager.getConnection(url,username,password); 
		PreparedStatement stat= conn.prepareStatement("SELECT * FROM user_auth where username=? and password =?");
		stat.setString(1,strName);
		stat.setString(2,strPass);
		rs = stat.executeQuery();
		if(rs.next())
		{
			
			String str1,str2;
			str1=rs.getString(1);
			str2=rs.getString(2);
			out.println(str1+str2);
			session.setAttribute("user",strName);
			if(str1.equals("admin"))
			{
				response.sendRedirect("Admin.htm");
			}
			else
			{
				out.println("Logged IN");
				response.sendRedirect("SearchCriteria.jsp");
			}
		}       
		else
		{
			response.sendRedirect("InvalidUser.htm");
		}
	}
	catch(Exception E)
	{
		out.println("Error "+E);
	}
	finally
	{
		rs.close();
		conn.close();
	}
	
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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
