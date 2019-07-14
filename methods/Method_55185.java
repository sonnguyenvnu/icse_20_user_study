/** 
 * Returns a Boolean value that indicates whether instances of a class respond to a particular selector. <p>You should usually use NSObject's respondsToSelector: or instancesRespondToSelector: methods instead of this function.</p>
 * @param cls  the class you want to inspect
 * @param name a selector
 * @return {@link #YES} if instances of the class respond to the selector, otherwise {@link #NO}
 */
@NativeType("BOOL") public static boolean class_respondsToSelector(@NativeType("Class") long cls,@NativeType("SEL") long name){
  long __functionAddress=Functions.class_respondsToSelector;
  if (CHECKS) {
    check(cls);
    check(name);
  }
  return invokePPZ(cls,name,__functionAddress);
}
