@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  try {
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    Project project=getProject(request);
    int rowIndex=Integer.parseInt(request.getParameter("row"));
    int cellIndex=Integer.parseInt(request.getParameter("cell"));
    String type=request.getParameter("type");
    String valueString=request.getParameter("value");
    Serializable value=null;
    if ("number".equals(type)) {
      value=Double.parseDouble(valueString);
    }
 else     if ("boolean".equals(type)) {
      value="true".equalsIgnoreCase(valueString);
    }
 else     if ("date".equals(type)) {
      value=ParsingUtilities.stringToDate(valueString);
    }
 else {
      value=valueString;
    }
    EditOneCellProcess process=new EditOneCellProcess(project,"Edit single cell",rowIndex,cellIndex,value);
    HistoryEntry historyEntry=project.processManager.queueProcess(process);
    if (historyEntry != null) {
      Pool pool=new Pool();
      if (process.newCell != null && process.newCell.recon != null) {
        pool.pool(process.newCell.recon);
      }
      respondJSON(response,new EditResult("ok",historyEntry,process.newCell,pool));
    }
 else {
      respond(response,"{ \"code\" : \"pending\" }");
    }
  }
 catch (  Exception e) {
    respondException(response,e);
  }
}
