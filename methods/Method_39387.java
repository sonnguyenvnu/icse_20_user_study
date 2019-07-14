/** 
 * Resolves constructor injection point from type. Looks for single annotated constructor. If no annotated constructors found, the total number of constructors will be checked. If there is only one constructor, that one will be used as injection point. If more constructors exist, the default one will be used as injection point. Otherwise, exception is thrown.
 */
public CtorInjectionPoint resolve(final Class type,final boolean useAnnotation){
  ClassDescriptor cd=ClassIntrospector.get().lookup(type);
  CtorDescriptor[] allCtors=cd.getAllCtorDescriptors();
  Constructor foundedCtor=null;
  Constructor defaultCtor=null;
  BeanReferences[] references=null;
  for (  CtorDescriptor ctorDescriptor : allCtors) {
    Constructor<?> ctor=ctorDescriptor.getConstructor();
    Class<?>[] paramTypes=ctor.getParameterTypes();
    if (paramTypes.length == 0) {
      defaultCtor=ctor;
    }
    if (!useAnnotation) {
      continue;
    }
    BeanReferences[] ctorReferences=referencesResolver.readAllReferencesFromAnnotation(ctor);
    if (ctorReferences == null) {
      continue;
    }
    if (foundedCtor != null) {
      throw new PetiteException("Two or more constructors are annotated as injection points in the bean: " + type.getName());
    }
    foundedCtor=ctor;
    references=ctorReferences;
  }
  if (foundedCtor == null) {
    if (allCtors.length == 1) {
      foundedCtor=allCtors[0].getConstructor();
    }
 else {
      foundedCtor=defaultCtor;
    }
    if (foundedCtor == null) {
      return CtorInjectionPoint.EMPTY;
    }
    references=referencesResolver.readAllReferencesFromAnnotation(foundedCtor);
    if (references == null) {
      references=new BeanReferences[0];
    }
  }
  return new CtorInjectionPoint(foundedCtor,references);
}
