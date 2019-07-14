@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  try {
    Project project=getProject(request);
    String oldColumnName=request.getParameter("oldColumnName");
    String newColumnName=request.getParameter("newColumnName");
    AbstractOperation op=new ColumnRenameOperation(oldColumnName,newColumnName);
    Process process=op.createProcess(project,new Properties());
    performProcessAndRespond(request,response,project,process);
  }
 catch (  Exception e) {
    respondException(response,e);
  }
}
