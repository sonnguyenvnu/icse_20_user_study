protected void putParameter(final String name,final String value){
  if (requestParameters == null) {
    requestParameters=new HashMap<>();
  }
  String[] params=requestParameters.get(name);
  if (params != null) {
    params=ArraysUtil.append(params,value);
  }
 else {
    params=new String[]{value};
  }
  requestParameters.put(name,params);
}
