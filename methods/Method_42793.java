public String getString(String name,String defaultValue){
  String resultStr=getRequest().getParameter(name);
  if (resultStr == null || "".equals(resultStr) || "null".equals(resultStr) || "undefined".equals(resultStr)) {
    return defaultValue;
  }
 else {
    return resultStr;
  }
}
