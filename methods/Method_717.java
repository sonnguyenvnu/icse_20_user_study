private Object strangeCodeForJackson(Object obj){
  if (obj != null) {
    String className=obj.getClass().getName();
    if ("com.fasterxml.jackson.databind.node.ObjectNode".equals(className)) {
      return obj.toString();
    }
  }
  return obj;
}
