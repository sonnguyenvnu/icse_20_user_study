/** 
 * Finds type data of first annotated superclass or interface.
 */
protected TypeData findSubclassTypeData(final Class type){
  final Class<? extends Annotation> defaultAnnotation=jsonAnnotation;
  if (type.getAnnotation(defaultAnnotation) != null) {
    return null;
  }
  ClassDescriptor cd=ClassIntrospector.get().lookup(type);
  Class[] superClasses=cd.getAllSuperclasses();
  for (  Class superClass : superClasses) {
    if (superClass.getAnnotation(defaultAnnotation) != null) {
      return _lookupTypeData(superClass);
    }
  }
  Class[] interfaces=cd.getAllInterfaces();
  for (  Class interfaze : interfaces) {
    if (interfaze.getAnnotation(defaultAnnotation) != null) {
      return _lookupTypeData(interfaze);
    }
  }
  return null;
}
