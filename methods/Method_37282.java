/** 
 * Inspects all declared constructors of a target type.
 */
protected CtorDescriptor[] inspectConstructors(){
  Class type=classDescriptor.getType();
  Constructor[] ctors=type.getDeclaredConstructors();
  CtorDescriptor[] allCtors=new CtorDescriptor[ctors.length];
  for (int i=0; i < ctors.length; i++) {
    Constructor ctor=ctors[i];
    CtorDescriptor ctorDescriptor=createCtorDescriptor(ctor);
    allCtors[i]=ctorDescriptor;
    if (ctorDescriptor.isDefault()) {
      defaultCtor=ctorDescriptor;
    }
  }
  return allCtors;
}
