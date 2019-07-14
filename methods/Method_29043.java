/** 
 * ?parameters???????"&"???http??????
 * @param parameters
 * @return
 */
private static String generatorParamString(Map<String,String> parameters,String encoding){
  StringBuffer params=new StringBuffer();
  if (parameters != null) {
    for (Iterator<String> iter=parameters.keySet().iterator(); iter.hasNext(); ) {
      String name=iter.next();
      String value=parameters.get(name);
      params.append(name + "=");
      try {
        params.append(URLEncoder.encode(value,encoding));
      }
 catch (      UnsupportedEncodingException e) {
        throw new RuntimeException(e.getMessage(),e);
      }
catch (      Exception e) {
        String message=String.format("'%s'='%s'",name,value);
        throw new RuntimeException(message,e);
      }
      if (iter.hasNext())       params.append("&");
    }
  }
  return params.toString();
}
