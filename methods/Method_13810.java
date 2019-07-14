@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  DatabaseConfiguration databaseConfiguration=getJdbcConfiguration(request);
  String query=request.getParameter("queryString");
  if (logger.isDebugEnabled()) {
    logger.debug("QueryCommand::Post::DatabaseConfiguration::{}::Query::{} ",databaseConfiguration,query);
  }
  try {
    response.setCharacterEncoding("UTF-8");
    response.setHeader("Content-Type","application/json");
    Writer w=response.getWriter();
    JsonGenerator writer=ParsingUtilities.mapper.getFactory().createGenerator(w);
    try {
      DatabaseInfo databaseInfo=DatabaseService.get(databaseConfiguration.getDatabaseType()).executeQuery(databaseConfiguration,query);
      ObjectMapper mapperObj=new ObjectMapper();
      response.setStatus(HttpStatus.SC_OK);
      String jsonStr=mapperObj.writeValueAsString(databaseInfo);
      if (logger.isDebugEnabled()) {
        logger.debug("QueryCommand::Post::Result::{} ",jsonStr);
      }
      writer.writeStartObject();
      writer.writeStringField("code","ok");
      writer.writeStringField("QueryResult",jsonStr);
      writer.writeEndObject();
    }
 catch (    DatabaseServiceException e) {
      logger.error("QueryCommand::Post::DatabaseServiceException::{}",e);
      sendError(HttpStatus.SC_BAD_REQUEST,response,e);
    }
catch (    Exception e) {
      logger.error("QueryCommand::Post::Exception::{}",e);
      sendError(HttpStatus.SC_BAD_REQUEST,response,e);
    }
 finally {
      writer.flush();
      writer.close();
      w.close();
    }
  }
 catch (  Exception e) {
    logger.error("QueryCommand::Post::Exception::{}",e);
    throw new ServletException(e);
  }
}
