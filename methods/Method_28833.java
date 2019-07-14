protected void addParam(String name,Object value){
  if (params == null) {
    params=new HashMap<String,Object>();
  }
  params.put(name,value);
}
