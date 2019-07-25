/** 
 * ?????easyui ??treegrid JSON??
 * @param response
 * @param dataGrid
 */
public static void treegrid(HttpServletResponse response,DataGrid dg){
  response.setContentType("application/json");
  response.setHeader("Cache-Control","no-store");
  String jsonStr=TagUtil.getJson(dg);
  JSONObject object=JSONObject.parseObject(jsonStr);
  JSONArray rows=object.getJSONArray("rows");
  try {
    PrintWriter pw=response.getWriter();
    pw.write(rows.toString());
    pw.flush();
    pw.close();
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
 finally {
    try {
      jsonStr=null;
      object.clear();
      dg.clear();
      dg=null;
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
}
