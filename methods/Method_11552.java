public Request putExtra(String key,Object value){
  if (extras == null) {
    extras=new HashMap<String,Object>();
  }
  extras.put(key,value);
  return this;
}
