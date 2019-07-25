public Map<Class,Map<String,Record>> dump(){
  Class clazz=this.getClass();
  return Collections.singletonMap(clazz,Collections.<String,Record>emptyMap());
}
