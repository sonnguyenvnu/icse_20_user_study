private ClassMirror place(ClassMirror r1){
  r1.mirrors=this;
  ClassMirror r2=cachedClasses.putIfAbsent(r1.getName(),r1);
  return r2 == null ? r1 : r2;
}
