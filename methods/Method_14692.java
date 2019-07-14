static public int[] getIntArray(ObjectNode obj,String key){
  ArrayNode a=getArray(obj,key);
  if (a == null) {
    return new int[0];
  }
  int[] r=new int[a.size()];
  int i=0;
  for (  JsonNode n : a) {
    r[i]=n.asInt();
    i++;
  }
  return r;
}
