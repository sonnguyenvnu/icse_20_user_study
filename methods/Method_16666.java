protected JobDataMap createJobDataMap(String parameters){
  JobDataMap map=new JobDataMap();
  if (!StringUtils.isEmpty(parameters)) {
    JSONArray jsonArray=JSON.parseArray(parameters);
    for (int i=0; i < jsonArray.size(); i++) {
      JSONObject o=jsonArray.getJSONObject(i);
      map.put(o.getString("key"),o.get("value"));
    }
  }
  return map;
}
