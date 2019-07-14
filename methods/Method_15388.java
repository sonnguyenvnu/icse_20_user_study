@Override public Object onFunctionParse(JSONObject json,String fun) throws Exception {
  return function.invoke(json,fun);
}
