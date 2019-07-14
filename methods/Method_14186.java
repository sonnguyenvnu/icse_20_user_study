@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  try {
    Project project=getProject(request);
    AbstractOperation op;
    String startColumnName=request.getParameter("startColumnName");
    int columnCount=Integer.parseInt(request.getParameter("columnCount"));
    boolean ignoreBlankCells=Boolean.parseBoolean(request.getParameter("ignoreBlankCells"));
    boolean fillDown=Boolean.parseBoolean(request.getParameter("fillDown"));
    String combinedColumnName=request.getParameter("combinedColumnName");
    if (combinedColumnName != null) {
      boolean prependColumnName=Boolean.parseBoolean(request.getParameter("prependColumnName"));
      String separator=request.getParameter("separator");
      op=new TransposeColumnsIntoRowsOperation(startColumnName,columnCount,ignoreBlankCells,fillDown,combinedColumnName,prependColumnName,separator);
    }
 else {
      String keyColumnName=request.getParameter("keyColumnName");
      String valueColumnName=request.getParameter("valueColumnName");
      op=new TransposeColumnsIntoRowsOperation(startColumnName,columnCount,ignoreBlankCells,fillDown,keyColumnName,valueColumnName);
    }
    Process process=op.createProcess(project,new Properties());
    performProcessAndRespond(request,response,project,process);
  }
 catch (  Exception e) {
    respondException(response,e);
  }
}
