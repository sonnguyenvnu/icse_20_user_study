@Override public Set<Class<?>> getErasedSuperTypeSet(){
  Set<Class<?>> result=new HashSet<>();
  result.add(Object.class);
  return getErasedSuperTypeSet(this.clazz,result);
}
