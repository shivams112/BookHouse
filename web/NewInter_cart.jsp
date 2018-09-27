<%@  page errorPage="errorpage.jsp" language="java" %>

<%
	int i;
	String bookid=null;
	String user_src=null;
	user_src=(String)session.getAttribute("user");
	if (user_src!=null)
   	{
		int counter=0;
		int total_sel=Integer.parseInt((String)session.getAttribute("TotalSel"));
		counter=Integer.parseInt((String)session.getAttribute("ctr_val"));
		int ctr=total_sel;
		for(i=1;i<=counter;i++)
		{
			if(request.getParameter("chk"+i)!=null)
			{
				bookid=request.getParameter("chk"+i);
				ctr=ctr+1;
				session.setAttribute("chk_var"+ctr,bookid);
			}
		}
		session.setAttribute("TotalSel",String.valueOf(ctr));	
		response.sendRedirect("Cart.jsp");
	}
	else
	{
		out.println("Unauthorised Shopping is not allowed");
	}
%>
