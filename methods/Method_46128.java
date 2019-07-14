public String toJson(){
  parameters.put(APP_NAME_KEY,this.appName);
  String result=JSON.toJSONString(parameters);
  return result;
}
