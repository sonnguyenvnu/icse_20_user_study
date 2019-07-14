@Override public String getCacheId(User data){
  return data == null ? null : "" + data.getId();
}
