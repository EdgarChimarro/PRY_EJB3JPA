<%@page import="model.Article"%>
<%@page import="model.Section"%>
<%@page import="model.Edition"%>
<%@page import="model.Catalog"%>
<%@page import="model.view.CatalogSessionBeanFacadeRemote"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cliente EJB3</title>
</head>
<body>
<% 
InitialContext cntext = new InitialContext();
CatalogSessionBeanFacadeRemote beanRemote = (CatalogSessionBeanFacadeRemote) cntext.lookup("EJB3-SessionEJB#model.view.CatalogSessionBeanFacadeRemote");
  
 beanRemote.createTestData();
 List<Catalog> catalogs = beanRemote.getAllCatalogs();
 List<Edition> editions = beanRemote.getAllEditions();
 List<Article> articles = beanRemote.getAllArticles();
 
 
 out.println("<br/>" + "List of Catalogs" + "<br/>");
 for (Catalog catalog : catalogs){
 	out.println("Catalog ID:");
 	out.println("<br/>" + catalog.getId() + "<br/>");
 	out.println("Catalog Journal:");
      out.println(catalog.getJournal() + "<br/>");
 	
 }
 
 beanRemote.deleteSomeData();
 
 
 out.println("<br/>" + "List of Editions" + "<br/>");
    editions = beanRemote.getAllEditions();
    for (Edition edition : editions) {
      out.println("Edition Id:");
      out.println(edition.getId() + "<br/>");
      out.println("Edition Date:");
      out.println(edition.getEdition() + "<br/>");

    }
   
    out.println("<br/>" + "List of Articles" + "<br/>");
    articles = beanRemote.getAllArticles();
    for (Article article : articles) {
      out.println("Article Id:");
      out.println(article.getId() + "<br/>");
      out.println("Article Title:");
      out.println(article.getTitle() + "<br/>");
      }
 
 %>

</body>
</html>