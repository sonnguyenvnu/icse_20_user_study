/** 
 * ?????easyui ??datagrid JSON??
 * @param response
 * @param dataGrid
 */
public static void datagrid(HttpServletResponse response,DataGrid dg){
  response.setContentType("application/json");
  response.setHeader("Cache-Control","no-store");
  String jsonStr=TagUtil.getJson(dg);
  try {
    PrintWriter pw=response.getWriter();
    pw.write(jsonStr.toString());
    pw.flush();
    pw.close();
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
 finally {
    try {
      jsonStr=null;
      dg.clear();
      dg=null;
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
}
