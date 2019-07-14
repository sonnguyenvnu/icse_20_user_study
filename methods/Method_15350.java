@Override public Object onFunctionParse(JSONObject json,String fun) throws Exception {
  if (function == null) {
    function=new DemoFunction(requestMethod,session);
  }
  return function.invoke(fun,json);
}
