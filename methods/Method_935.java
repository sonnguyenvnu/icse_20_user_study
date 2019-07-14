@Override public <K,V>Map<K,V> getNameMap(String className,String name,Class<K> kClass,Class<V> vClass){
  Gson gson=new Gson();
  return gson.fromJson((String)PROPERTIES.get(className + SEPARATOR + name),new TypeToken<Map<K,V>>(){
  }
.getType());
}
