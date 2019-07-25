public JSONArray export(String serviceName,String tableName) throws IOException {
  JSONArray a=new JSONArray();
  Iterator<String> idi=this.db.ids(serviceName,tableName);
  while (idi.hasNext()) {
    String id=idi.next();
    JSONObject j=read(serviceName,tableName,id);
    if (j != null)     a.put(j);
  }
  return a;
}
