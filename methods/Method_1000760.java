public static NutMap invoke(Object obj,NutMap req){
  NutMap resp=new NutMap();
  String version=req.getString("jsonrpc",Version);
  String id=req.getString("id");
  resp.setv("id",id);
  resp.setv("jsonrpc",version);
  String method=req.getString("method");
  if (Strings.isBlank(method)) {
    return resp.setv("error",error(InvalidRequest,"Invalid Request","method name is blank"));
  }
  List<Object> params=req.getList("params",Object.class,Collections.EMPTY_LIST);
  Invoking ing;
  try {
    ing=Mirror.me(obj).getInvoking(method,params.toArray());
  }
 catch (  Exception e) {
    return resp.setv("error",error(MethodNotFound,"Method not found",E(e)));
  }
  try {
    return resp.setv("result",ing.invoke(obj));
  }
 catch (  Exception e) {
    return resp.setv("error",error(ServerError,e.getMessage(),E(e)));
  }
catch (  Throwable e) {
    StringWriter sw=new StringWriter();
    PrintWriter writer=new PrintWriter(sw);
    e.printStackTrace(writer);
    return resp.setv("error",error(InternalError,e.getMessage(),E(e)));
  }
}
