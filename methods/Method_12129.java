public String getJsonString(){
  Map<String,Object> m=new HashMap<>();
  m.put("id",getId());
  m.put("parentId",parentId);
  m.put("title",title);
  m.put("tvalue",tvalue);
  m.put("sequence",sequence);
  m.put("intro",intro);
  return JSONObject.toJSONString(m);
}
