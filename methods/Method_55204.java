/** 
 * Creates an instance of a class at the specified location.
 * @param cls   the class that you want to allocate an instance of
 * @param bytes the location at which to allocate an instance of the {@code cls} class. {@code bytes} must point to at least {@code class_getInstanceSize(cls)}bytes of well-aligned, zero-filled memory.
 * @return an instance of the class {@code cls} at {@code bytes}, if successful; otherwise  {@link #nil} (for example, if {@code cls} or {@code bytes} are themselves {@link #nil})
 */
@NativeType("id") public static long objc_constructInstance(@NativeType("Class") long cls,@Nullable @NativeType("void *") ByteBuffer bytes){
  if (CHECKS) {
    if (DEBUG) {
      checkSafe(bytes,class_getInstanceSize(cls));
    }
  }
  return nobjc_constructInstance(cls,memAddressSafe(bytes));
}
