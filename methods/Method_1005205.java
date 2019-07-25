/** 
 * ?????easyui ??datagrid JSON??
 * @param response
 * @param dataGrid
 * @param extMap ???????
 */
public static void datagrid(HttpServletResponse response,DataGrid dg,Map<String,Map<String,Object>> extMap){
  response.setContentType("application/json");
  response.setHeader("Cache-Control","no-store");
  String jsonStr=TagUtil.getJson(dg);
  JSONObject object=JSONObject.parseObject(jsonStr);
  JSONArray r=object.getJSONArray("rows");
  for (  Object object2 : r) {
    JSONObject o=(JSONObject)object2;
    o.putAll(extMap.get(o.get("id")));
  }
  try {
    PrintWriter pw=response.getWriter();
    pw=response.getWriter();
    pw.write(object.toString());
    pw.flush();
    pw.close();
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
 finally {
    try {
      object.clear();
      dg.clear();
      jsonStr=null;
      dg=null;
      extMap=null;
    }
 catch (    Exception e2) {
    }
  }
}
