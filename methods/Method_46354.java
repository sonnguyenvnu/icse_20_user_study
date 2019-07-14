@Override public String getFromApp(Map<String,String> tagsWithStr){
  return tagsWithStr.get(RpcSpanTags.LOCAL_APP);
}
