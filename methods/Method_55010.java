/** 
 * Returns the body of the primitive array. The result is valid until the  {@link #ReleaseByteArrayElements} function is called. Since the returned arraymay be a copy of the Java array, changes made to the returned array will not necessarily be reflected in the original array until {@link #ReleaseByteArrayElements} is called.<p>If  {@code isCopy} is not {@code NULL}, then  {@code *isCopy} is set to {@link #JNI_TRUE TRUE} if a copy is made; or it is set to {@link #JNI_FALSE FALSE} if no copy is made.</p>
 * @param array  the primitive array
 * @param isCopy a pointer to a boolean
 * @return a pointer to the array elements, or {@code NULL} if the operation fails
 */
@Nullable @NativeType("jbyte *") public static ByteBuffer GetByteArrayElements(@NativeType("jbyteArray") byte[] array,@Nullable @NativeType("jboolean *") ByteBuffer isCopy){
  if (CHECKS) {
    checkSafe(isCopy,1);
  }
  long __result=nGetByteArrayElements(array,memAddressSafe(isCopy));
  return memByteBufferSafe(__result,array.length);
}
