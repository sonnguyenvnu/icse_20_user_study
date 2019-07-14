protected void addParam(String name){
  if (params == null) {
    params=new HashMap<String,Object>();
  }
  params.put(name,null);
}
