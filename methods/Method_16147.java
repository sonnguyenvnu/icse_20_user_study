public Map<String,Object> toSimpleMap(Function<Object,Serializable> objectFilter,Map<String,Object> map){
  map.put("action",action);
  map.put("describe",describe);
  if (method != null) {
    StringJoiner methodAppender=new StringJoiner(",",method.getName().concat("("),")");
    String[] parameterNames=parameters.keySet().toArray(new String[parameters.size()]);
    Class[] parameterTypes=method.getParameterTypes();
    for (int i=0; i < parameterTypes.length; i++) {
      methodAppender.add(parameterTypes[i].getSimpleName().concat(" ").concat(parameterNames.length > i ? parameterNames[i] : ("arg" + i)));
    }
    map.put("method",methodAppender.toString());
  }
  map.put("target",target != null ? target.getName() : "");
  Map<String,Object> newParameter=new LinkedHashMap<>(parameters);
  newParameter.entrySet().forEach(entry -> {
    if (entry.getValue() != null) {
      entry.setValue(objectFilter.apply(entry.getValue()));
    }
  }
);
  map.put("parameters",newParameter);
  map.put("httpHeaders",httpHeaders);
  map.put("httpMethod",httpMethod);
  map.put("ip",ip);
  map.put("url",url);
  map.put("response",objectFilter.apply(response));
  map.put("requestTime",requestTime);
  map.put("responseTime",responseTime);
  map.put("id",id);
  map.put("useTime",responseTime - requestTime);
  if (exception != null) {
    StringWriter writer=new StringWriter();
    exception.printStackTrace(new PrintWriter(writer));
    map.put("exception",writer.toString());
  }
  return map;
}
