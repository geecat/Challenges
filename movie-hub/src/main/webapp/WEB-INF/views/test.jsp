<html><body>  
<%! public void jspInit(){  
    ServletConfig Sconfig = getServletConfig();   
    ServletContext Scontext = getServletContext();   
    String email = Sconfig.getInitParameter("email");   
    Scontext.setAttribute("email",email);  
    Scontext.setAttribute("myMessage","This is my message");  
 }%>  
Email Via ServletContext : <%= getServletContext().getAttribute("email") %> <br>  
Email Via ServletConfig  : <%= getServletConfig().getInitParameter("email") %> <br>  
Message Via ServletContext  : <%= getServletContext().getAttribute("myMessage") %> <br>  
<br>  
Above is example of overriding jspInit() method.   
</body></html>  