private String toString(Object object){
  if (object instanceof Map) {
    return JSON.toJSONString(object);
  }
 else {
    return String.valueOf(object);
  }
}
