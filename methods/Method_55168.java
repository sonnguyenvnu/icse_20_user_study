/** 
 * Returns the class definition of a specified class. <p> {@link #objc_getClass} is different from this function in that if the class is not registered, objc_getClass calls the class handler callback and then checksa second time to see whether the class is registered. This function does not call the class handler callback.</p>
 * @param name the name of the class to look up
 * @return the Class object for the named class, or {@link #nil} if the class is not registered with the Objective-C runtime
 */
@NativeType("Class") public static long objc_lookUpClass(@NativeType("char const *") ByteBuffer name){
  if (CHECKS) {
    checkNT1(name);
  }
  return nobjc_lookUpClass(memAddress(name));
}
