@Override default int count(Entity param){
  return createRequest("/count",param).get().as(Integer.class);
}
