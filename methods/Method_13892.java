private void doListDocuments(HttpServletRequest request,HttpServletResponse response,Properties parameters) throws ServletException, IOException {
  String token=TokenCookie.getToken(request);
  if (token == null) {
    HttpUtilities.respond(response,"error","Not authorized");
    return;
  }
  Writer w=response.getWriter();
  JsonGenerator writer=ParsingUtilities.mapper.getFactory().createGenerator(w);
  try {
    writer.writeStartObject();
    writer.writeArrayFieldStart("documents");
    try {
      listSpreadsheets(GoogleAPIExtension.getDriveService(token),writer);
      listFusionTables(FusionTableHandler.getFusionTablesService(token),writer);
    }
 catch (    Exception e) {
      logger.error("doListDocuments exception:" + ExceptionUtils.getStackTrace(e));
    }
 finally {
      writer.writeEndArray();
      writer.writeEndObject();
    }
  }
 catch (  IOException e) {
    throw new ServletException(e);
  }
 finally {
    writer.flush();
    writer.close();
    w.flush();
    w.close();
  }
}
