/** 
 * Returns an array of method descriptions of methods meeting a given specification for a given protocol. <p>Methods in other protocols adopted by this protocol are not included.</p>
 * @param p                a protocol
 * @param isRequiredMethod a Boolean value that indicates whether returned methods should be required methods (pass {@link #YES} to specify required methods)
 * @param isInstanceMethod a Boolean value that indicates whether returned methods should be instance methods (pass {@link #YES} to specify instance methods)
 * @return a C array of objc_method_description structures containing the names and types of {@code p}'s methods specified by  {@code isRequiredMethod} and{@code isInstanceMethod}. The array contains  {@code *outCount} pointers followed by a {@code NULL} terminator. You must free the list with free().<p>If the protocol declares no methods that meet the specification,  {@code NULL} is returned and {@code *outCount} is 0.</p>
 */
@Nullable @NativeType("struct objc_method_description *") public static ObjCMethodDescription.Buffer protocol_copyMethodDescriptionList(@NativeType("Protocol *") long p,@NativeType("BOOL") boolean isRequiredMethod,@NativeType("BOOL") boolean isInstanceMethod){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  IntBuffer outCount=stack.callocInt(1);
  try {
    long __result=nprotocol_copyMethodDescriptionList(p,isRequiredMethod,isInstanceMethod,memAddress(outCount));
    return ObjCMethodDescription.createSafe(__result,outCount.get(0));
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
