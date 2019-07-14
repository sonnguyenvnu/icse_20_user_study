public static boolean containsJson(HttpParameter[] params){
  return params.length == 1 && params[0].isJson();
}
