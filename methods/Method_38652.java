@Override public void serializeValue(final JsonContext jsonContext,final JsonObject jsonObject){
  jsonContext.writeOpenObject();
  int count=0;
  Path currentPath=jsonContext.getPath();
  for (  Map.Entry<String,?> entry : jsonObject.map().entrySet()) {
    String key=entry.getKey();
    Object value=entry.getValue();
    count=serializeKeyValue(jsonContext,currentPath,key,value,count);
  }
  jsonContext.writeCloseObject();
}
