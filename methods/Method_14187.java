@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  try {
    Project project=getProject(request);
    String columnName=request.getParameter("columnName");
    int rowCount=Integer.parseInt(request.getParameter("rowCount"));
    AbstractOperation op=new TransposeRowsIntoColumnsOperation(columnName,rowCount);
    Process process=op.createProcess(project,new Properties());
    performProcessAndRespond(request,response,project,process);
  }
 catch (  Exception e) {
    respondException(response,e);
  }
}
