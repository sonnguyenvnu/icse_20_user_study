@RequestMapping(params="datagrid") public void datagrid(HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  String tableName=request.getParameter("id");
  List<String> list=new ArrayList<String>();
  try {
    list=new JeecgReadTable().readAllTableNames();
  }
 catch (  SQLException e1) {
    e1.printStackTrace();
  }
  String html="";
  Collections.sort(list,new StringSort(SortDirection.toEnum(dataGrid.getOrder())));
  List<String> tables=cgFormFieldService.findByQueryString("select tableName from CgFormHeadEntity");
  list.removeAll(tables);
  List<String> index=new ArrayList<String>();
  if (StringUtil.isNotEmpty(tableName)) {
    for (int i=0; i < list.size(); i++) {
      if (list.get(i).contains(tableName))       index.add(list.get(i));
    }
    html=getJson(index,index.size());
  }
 else   html=getJson(list,list.size());
  PrintWriter writer=null;
  try {
    response.setContentType("text/html");
    response.setHeader("Cache-Control","no-store");
    writer=response.getWriter();
    writer.println(html);
    writer.flush();
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
 finally {
    try {
      writer.close();
    }
 catch (    Exception e2) {
    }
  }
}
