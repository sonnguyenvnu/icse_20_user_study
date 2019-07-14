public void addUserParam(String key,Object value){
  if (userParams == null) {
    userParams=new ArrayMap<>(32);
  }
  userParams.put(key,value);
}
