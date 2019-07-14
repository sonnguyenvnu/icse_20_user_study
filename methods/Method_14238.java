@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  try {
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    Project project=getProject(request);
    int rowIndex=Integer.parseInt(request.getParameter("row"));
    int cellIndex=Integer.parseInt(request.getParameter("cell"));
    Judgment judgment=Recon.stringToJudgment(request.getParameter("judgment"));
    ReconCandidate match=null;
    String id=request.getParameter("id");
    if (id != null) {
      String scoreString=request.getParameter("score");
      match=new ReconCandidate(id,request.getParameter("name"),request.getParameter("types").split(","),scoreString != null ? Double.parseDouble(scoreString) : 100);
    }
    JudgeOneCellProcess process=new JudgeOneCellProcess(project,"Judge one cell's recon result",judgment,rowIndex,cellIndex,match,request.getParameter("identifierSpace"),request.getParameter("schemaSpace"));
    HistoryEntry historyEntry=project.processManager.queueProcess(process);
    if (historyEntry != null) {
      Pool pool=new Pool();
      if (process.newCell != null && process.newCell.recon != null) {
        pool.pool(process.newCell.recon);
      }
      respondJSON(response,new ReconClearOneCellCommand.CellResponse(historyEntry,process.newCell,pool));
    }
 else {
      respond(response,"{ \"code\" : \"pending\" }");
    }
  }
 catch (  Exception e) {
    respondException(response,e);
  }
}
