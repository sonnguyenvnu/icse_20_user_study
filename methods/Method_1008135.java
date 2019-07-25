public static String stringify(Object o) throws IOException {
  Object v=toJsonValue(o);
  try {
    return v instanceof String ? jsonMapper.writeValueAsString(v) : v.toString();
  }
 catch (  IOException e) {
    Loggers.getLogger(ClusterService.class).error("Unexpected json encoding error",e);
    throw new RuntimeException(e);
  }
}
