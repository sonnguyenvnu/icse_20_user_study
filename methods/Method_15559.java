@Override public List<JSONObject> getCache(String sql,int type){
  return cacheMap.get(sql);
}
