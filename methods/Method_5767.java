private static void addValues(HashMap<String,byte[]> metadata,Map<String,Object> values){
  for (  String name : values.keySet()) {
    Object value=values.get(name);
    byte[] bytes=getBytes(value);
    if (bytes.length > MAX_VALUE_LENGTH) {
      throw new IllegalArgumentException("The size of " + name + " (" + bytes.length + ") is greater than maximum allowed: " + MAX_VALUE_LENGTH);
    }
    metadata.put(name,bytes);
  }
}
