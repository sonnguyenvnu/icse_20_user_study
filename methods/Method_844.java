public static String getMessage(String key,Object... params){
  String value=getMessage(key);
  if (params == null || params.length == 0) {
    return value;
  }
  return String.format(value,params);
}
