/** 
 * Returns a specified instance method for a given class. <p>Note that this function searches superclasses for implementations, whereas  {@link #class_copyMethodList} does not.</p>
 * @param cls  the class you want to inspect
 * @param name the selector of the method you want to retrieve
 * @return the method that corresponds to the implementation of the selector specified by aSelector for the class specified by {@code cls}, or  {@code NULL} if thespecified class or its superclasses do not contain an instance method with the specified selector.
 */
@NativeType("Method") public static long class_getInstanceMethod(@NativeType("Class") long cls,@NativeType("SEL") long name){
  long __functionAddress=Functions.class_getInstanceMethod;
  if (CHECKS) {
    check(cls);
    check(name);
  }
  return invokePPP(cls,name,__functionAddress);
}
