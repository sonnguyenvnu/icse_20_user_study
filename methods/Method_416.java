public static Object reserveToArray(Object object,String... paths){
  JSONArray reserved=new JSONArray();
  if (paths == null || paths.length == 0) {
    return reserved;
  }
  for (  String item : paths) {
    JSONPath path=JSONPath.compile(item);
    path.init();
    Object value=path.eval(object);
    reserved.add(value);
  }
  return reserved;
}
