/** 
 * Registers a method name with the Objective-C runtime system. <p>The implementation of this method is identical to the implementation of  {@link #sel_registerName}.</p>
 * @param str a pointer to a C string. Pass the name of the method you wish to register
 * @return a pointer of type SEL specifying the selector for the named method
 */
@NativeType("SEL") public static long sel_getUid(@NativeType("char const *") ByteBuffer str){
  if (CHECKS) {
    checkNT1(str);
  }
  return nsel_getUid(memAddress(str));
}
