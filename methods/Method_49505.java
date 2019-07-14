private String getInternalKey(String userKey){
  return null == prefix ? userKey : prefix + userKey;
}
