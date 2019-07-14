/** 
 * Registers native methods with the class specified by the  {@code targetClass} argument. The methods parameter specifies an array of JNINativeMethodstructures that contain the names, signatures, and function pointers of the native methods. The name and signature fields of the  {@link JNINativeMethod}structure are pointers to modified UTF-8 strings. The  {@code nMethods} parameter specifies the number of native methods in the array.
 * @param methods the native methods in the class
 * @return “0” on success; returns a negative value on failure
 */
@NativeType("jint") public static int RegisterNatives(@NativeType("jclass") Class<?> targetClass,@NativeType("JNINativeMethod const *") JNINativeMethod.Buffer methods){
  if (CHECKS) {
    JNINativeMethod.validate(methods.address(),methods.remaining());
  }
  return nRegisterNatives(targetClass,methods.address(),methods.remaining());
}
