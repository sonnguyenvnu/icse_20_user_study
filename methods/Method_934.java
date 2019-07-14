@Override public List<String> getNameList(String className,String name){
  Gson gson=new Gson();
  return gson.fromJson((String)PROPERTIES.get(className + SEPARATOR + name),new TypeToken<List<String>>(){
  }
.getType());
}
