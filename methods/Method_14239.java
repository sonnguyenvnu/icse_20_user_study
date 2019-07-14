@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  response.setCharacterEncoding("UTF-8");
  response.setHeader("Content-Type","application/json");
  try {
    Project project=getProject(request);
    int rowIndex=Integer.parseInt(request.getParameter("row"));
    String starredString=request.getParameter("starred");
    if (starredString != null) {
      boolean starred="true".endsWith(starredString);
      String description=(starred ? "Star row " : "Unstar row ") + (rowIndex + 1);
      StarOneRowProcess process=new StarOneRowProcess(project,description,rowIndex,starred);
      performProcessAndRespond(request,response,project,process);
      return;
    }
    String flaggedString=request.getParameter("flagged");
    if (flaggedString != null) {
      boolean flagged="true".endsWith(flaggedString);
      String description=(flagged ? "Flag row " : "Unflag row ") + (rowIndex + 1);
      FlagOneRowProcess process=new FlagOneRowProcess(project,description,rowIndex,flagged);
      performProcessAndRespond(request,response,project,process);
      return;
    }
    respond(response,"{ \"code\" : \"error\", \"message\" : \"invalid command parameters\" }");
  }
 catch (  Exception e) {
    respondException(response,e);
  }
}
