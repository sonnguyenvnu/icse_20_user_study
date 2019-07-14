/** 
 * Decrements the reference count on the dynamic library handle handle. If the reference count drops to zero and no other loaded libraries use symbols in it, then the dynamic library is unloaded.
 * @param handle the dynamic library to close
 */
public static int dlclose(@NativeType("void *") long handle){
  if (CHECKS) {
    check(handle);
  }
  return ndlclose(handle);
}
