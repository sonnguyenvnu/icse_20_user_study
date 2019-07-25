public <T>Class<T> define(ClassDefiner cd,Class<T> klass,String newName){
  Class<T> newClass=try2Load(newName,klass.getClassLoader());
  if (newClass != null)   return newClass;
  if (!checkClass(klass))   return klass;
  Pair2[] pair2s=findMatchedMethod(klass);
  if (pair2s.length == 0)   return klass;
  Constructor<T>[] constructors=getEffectiveConstructors(klass);
  newClass=generate(cd,pair2s,newName,klass,constructors);
  return newClass;
}
