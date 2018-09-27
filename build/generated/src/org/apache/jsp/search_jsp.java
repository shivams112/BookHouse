package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class search_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>Search Results</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body bgcolor=\"#CCFFFF\">\r\n");
      out.write("\r\n");
      out.write("<CENTER>\r\n");
      out.write("<H1> <u>Online Shopping Cart</u> </H1>\r\n");
      out.write("<font size=\"5\" >Search Results</font>\r\n");
      out.write("</CENTER>\r\n");
      out.write("<BR>\r\n");
      out.write("<a href=\"Logout.jsp\" ><font size=\"4\"  >Click here to Logout</font></a>\r\n");
      out.write("\r\n");

	
	String str_searchvar=null;
	int chk_ctr=0;
	String str_colvar=null;
	String tab_var="";
	String user_src=(String)session.getAttribute("user");
	System.out.println("0");	
	if (user_src!=null)
   	{
		String query=null;
		Connection conn;
		conn=null;
		ResultSet rs;
		rs=null;
		if(request.getParameter("R1").equals("Title"))
		{
			str_searchvar=request.getParameter("BookTitle");
			str_colvar="TITLE";
		}
		if(request.getParameter("R1").equals("Category"))	
		{
			chk_ctr=1;
			str_searchvar=request.getParameter("BookCategory");
			str_colvar="Categoryname";
		}
		if(request.getParameter("R1").equals("Publisher"))	
		{
			str_searchvar=request.getParameter("BookPublisher");
			str_colvar="Publisher";
		}
		if(request.getParameter("R1").equals("Author"))
		{
			str_searchvar=request.getParameter("BookAuthor");
			str_colvar="Author";
		}		
		if (request.getParameter("R2")==null)
		{
			tab_var="BOOK_DETAILS";		
		}
		else
		{
		// to get the name of the table in which the search to be done-------
			if (request.getParameter("R2").equals("A"))
			{
				tab_var="TEMP_DETAIL";
			}
			else
			{
				tab_var="BOOK_DETAILS";
			}
		}


	System.out.println(str_searchvar);
       	query = "select a.* from "+tab_var+" a, category_details b where a."+str_colvar+" = ? and a.CATEGORYID = b.CATEGORYID"; 
	System.out.println("1");
	try
	{
	Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/bookhouse";
        String username = "root";
        String password = "1234";
        conn = DriverManager.getConnection(url,username,password); 
	int ctr=0;
	System.out.println("2");
	PreparedStatement stm = conn.prepareStatement(query);
	stm.setString(1,str_searchvar);
	rs = stm.executeQuery();

      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t<form name=\"cart\" action=\"Inter_Cart.jsp\" method=\"POST\">\r\n");
      out.write("\t\t\t<table border=1>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t<td>S.No</td>\r\n");
      out.write("\t\t\t<td>Select</td>\r\n");
      out.write("\t\t\t<td>Book Name</td>\r\n");
      out.write("\t\t\t<td>Author Name</td>\r\n");
      out.write("\t\t\t<td>Publisher</td>\r\n");
      out.write("\t\t\t<td>Edition</td>\r\n");
      out.write("\t\t\t<td>Price (in Rs.)</td>\r\n");
      out.write("\t\t\t<td>Quantity Available(Nos.)</td>\r\n");
      out.write("\t\t\t<td>Description</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\r\n");

	while(rs.next())
	{
	System.out.println("inside while");
	ctr=ctr+1;
	out.println("<tr><td>"+ctr+"</td>");
	int int_BOOKID=0;
	int_BOOKID=rs.getInt(1);
	System.out.println(int_BOOKID);	
	out.println("<td><input type=checkbox name=chk"+ctr+" value="+int_BOOKID+">");
				
	int int_CATEGORYID=rs.getInt(2);
					
	String str_TITLE=rs.getString(3);
	out.println("<td>");
	out.println(str_TITLE);
	out.println("</td>");

	String str_AUTHOR=rs.getString(4);
	out.println("<td>");
	out.println(str_AUTHOR);
	out.println("</td>");
		
	
	String str_PUBLISHER=rs.getString(5);
	out.println("<td>");
	out.println(str_PUBLISHER);
	out.println("</td>");
	
		
	String str_EDITION=rs.getString(6);
	out.println("<td>");
	out.println(str_EDITION);
	out.println("</td>");
		
	int int_PRICE=rs.getInt(7);
	out.println("<td>");
	out.println(int_PRICE);
	out.println("</td>");
		
	int int_QUANTITY=rs.getInt(8);
	out.println("<td>");
	out.println(int_QUANTITY);
	out.println("</td>");

	String str_DESCRIPTION=rs.getString(9);
	out.println("<td>");
	out.println(str_DESCRIPTION);
	out.println("</td></tr>");

	String ins_query ="INSERT INTO temp_detail VALUES (?,?,?,?,?,?,?,?,?)";
	
	PreparedStatement stat_ins= conn.prepareStatement(ins_query);
	stat_ins.setInt(1,int_BOOKID);
	stat_ins.setInt(2,int_CATEGORYID);
	stat_ins.setString(3,str_TITLE);
	stat_ins.setString(4,str_AUTHOR);
	stat_ins.setString(5,str_PUBLISHER);
	stat_ins.setString(6,str_EDITION);
	stat_ins.setInt(7,int_PRICE);
	stat_ins.setInt(8,int_QUANTITY);
	stat_ins.setString(9,str_DESCRIPTION); 

	int x = stat_ins.executeUpdate();	
	System.out.println(x);

	}	
		
			String str_ctr=String.valueOf(ctr);
			session.setAttribute("ctr_val",str_ctr);


      out.write("\r\n");
      out.write("\t</table>\r\n");
      out.write("\t<br>\r\n");
      out.write("\t<input type=\"submit\" value=\"    Add to Cart    \" name=\"Add\">\t\r\n");
      out.write("\t</form>\r\n");
      out.write("\r\n");


	rs.close();
	stm.close();
	}
	catch(Exception e){}
	}
	else
		{
			response.sendRedirect("Unauthorised.htm");
		}

      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\t");
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
