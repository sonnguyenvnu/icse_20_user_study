public static String stringify(Object[] cols,int length){
  if (cols.length == 1)   return toJsonValue(cols[0]).toString();
  StringBuilder sb=new StringBuilder();
  sb.append("[");
  for (int i=0; i < length; i++) {
    if (i > 0)     sb.append(",");
    Object val=toJsonValue(cols[i]);
    if (val instanceof String) {
      try {
        sb.append(jsonMapper.writeValueAsString(val));
      }
 catch (      IOException e) {
        Loggers.getLogger(ClusterService.class).error("Unexpected json encoding error",e);
        throw new RuntimeException(e);
      }
    }
 else {
      sb.append(val);
    }
  }
  return sb.append("]").toString();
}
