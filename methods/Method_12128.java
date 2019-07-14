public String getJsonString(){
  Map<String,Object> m=new HashMap<>();
  m.put("id",getId());
  m.put("sn",sn);
  m.put("title",title);
  m.put("intro",intro);
  return JSONObject.toJSONString(m);
}
