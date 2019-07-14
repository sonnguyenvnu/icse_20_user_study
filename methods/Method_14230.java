@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  ProjectManager.singleton.setBusy(true);
  try {
    Project project=getProject(request);
    Engine engine=getEngine(request,project);
    Properties params=getRequestParameters(request);
    String format=params.getProperty("format");
    Exporter exporter=ExporterRegistry.getExporter(format);
    if (exporter == null) {
      exporter=new CsvExporter('\t');
    }
    String contentType=params.getProperty("contentType");
    if (contentType == null) {
      contentType=exporter.getContentType();
    }
    response.setHeader("Content-Type",contentType);
    if (exporter instanceof WriterExporter) {
      String encoding=params.getProperty("encoding");
      response.setCharacterEncoding(encoding != null ? encoding : "UTF-8");
      Writer writer=encoding == null ? response.getWriter() : new OutputStreamWriter(response.getOutputStream(),encoding);
      ((WriterExporter)exporter).export(project,params,engine,writer);
      writer.close();
    }
 else     if (exporter instanceof StreamExporter) {
      response.setCharacterEncoding("UTF-8");
      OutputStream stream=response.getOutputStream();
      ((StreamExporter)exporter).export(project,params,engine,stream);
      stream.close();
    }
 else {
      respondException(response,new RuntimeException("Unknown exporter type"));
    }
  }
 catch (  Exception e) {
    logger.info("error:{}",e.getMessage());
    if (e instanceof SqlExporterException) {
      response.sendError(HttpStatus.SC_BAD_REQUEST,e.getMessage());
    }
    throw new ServletException(e);
  }
 finally {
    ProjectManager.singleton.setBusy(false);
  }
}
