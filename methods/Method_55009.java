/** 
 * Converts a field ID derived from  {@code cls} to a {@link Field} object.
 * @param isStatic must be set to {@link #JNI_TRUE TRUE} if {@code fieldID} refers to a static field, and {@link #JNI_FALSE FALSE} otherwise
 */
@Nullable @NativeType("jobject") public static Field ToReflectedField(@NativeType("jclass") Class<?> cls,@NativeType("jfieldID") long fieldID,@NativeType("jboolean") boolean isStatic){
  if (CHECKS) {
    check(fieldID);
  }
  return nToReflectedField(cls,fieldID,isStatic);
}
