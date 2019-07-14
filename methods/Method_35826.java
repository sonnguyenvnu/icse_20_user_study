public static Parameters from(Map<String,Object> parameterMap){
  Parameters parameters=new Parameters();
  parameters.putAll(parameterMap);
  return parameters;
}
