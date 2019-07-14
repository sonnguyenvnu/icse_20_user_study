@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  try {
    Project project=getProject(request);
    String keyColumnName=request.getParameter("keyColumnName");
    String valueColumnName=request.getParameter("valueColumnName");
    String noteColumnName=request.getParameter("noteColumnName");
    AbstractOperation op=new KeyValueColumnizeOperation(keyColumnName,valueColumnName,noteColumnName);
    Process process=op.createProcess(project,new Properties());
    performProcessAndRespond(request,response,project,process);
  }
 catch (  Exception e) {
    respondException(response,e);
  }
}
