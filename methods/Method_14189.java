@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  try {
    Project project=getProject(request);
    String columnName=request.getParameter("columnName");
    int index=Integer.parseInt(request.getParameter("index"));
    AbstractOperation op=new ColumnMoveOperation(columnName,index);
    Process process=op.createProcess(project,new Properties());
    performProcessAndRespond(request,response,project,process);
  }
 catch (  Exception e) {
    respondException(response,e);
  }
}
