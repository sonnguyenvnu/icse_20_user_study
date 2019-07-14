@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  Project project=getProject(request);
  long lastDoneID=-1;
  String lastDoneIDString=request.getParameter("lastDoneID");
  if (lastDoneIDString != null) {
    lastDoneID=Long.parseLong(lastDoneIDString);
  }
 else {
    String undoIDString=request.getParameter("undoID");
    if (undoIDString != null) {
      long undoID=Long.parseLong(undoIDString);
      lastDoneID=project.history.getPrecedingEntryID(undoID);
    }
  }
  try {
    boolean done=lastDoneID == -1 || project.processManager.queueProcess(new HistoryProcess(project,lastDoneID));
    respond(response,"{ \"code\" : " + (done ? "\"ok\"" : "\"pending\"") + " }");
  }
 catch (  Exception e) {
    respondException(response,e);
  }
}
