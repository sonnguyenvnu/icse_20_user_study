@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  DatabaseConfiguration databaseConfiguration=getJdbcConfiguration(request);
  if (logger.isDebugEnabled()) {
    logger.debug("ConnectCommand::Post::{}",databaseConfiguration);
  }
  try {
    response.setCharacterEncoding("UTF-8");
    response.setHeader("Content-Type","application/json");
    Writer w=response.getWriter();
    JsonGenerator writer=ParsingUtilities.mapper.getFactory().createGenerator(w);
    ObjectMapper mapperObj=new ObjectMapper();
    try {
      DatabaseInfo databaseInfo=DatabaseService.get(databaseConfiguration.getDatabaseType()).connect(databaseConfiguration);
      String databaseInfoString=mapperObj.writeValueAsString(databaseInfo);
      response.setStatus(HttpStatus.SC_OK);
      writer.writeStartObject();
      writer.writeStringField("code","ok");
      writer.writeStringField("databaseInfo",databaseInfoString);
      writer.writeEndObject();
    }
 catch (    DatabaseServiceException e) {
      logger.error("ConnectCommand::Post::DatabaseServiceException::{}",e);
      sendError(HttpStatus.SC_UNAUTHORIZED,response,e);
    }
catch (    Exception e) {
      logger.error("ConnectCommand::Post::Exception::{}",e);
      sendError(HttpStatus.SC_UNAUTHORIZED,response,e);
    }
 finally {
      writer.flush();
      writer.close();
      w.close();
    }
  }
 catch (  Exception e) {
    logger.error("ConnectCommand::Post::Exception::{}",e);
    throw new ServletException(e);
  }
}
