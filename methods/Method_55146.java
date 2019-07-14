/** 
 * Closes a dynamic library or bundle. <p>This function decreases the reference count of the image referenced by handle. When the reference count for handle becomes 0, the termination routines in the image are called, and the image is removed from the address space of the current process. After that point, handle is rendered invalid.</p>
 * @param handle a handle obtained through a call to {@link #dlopen}.
 */
public static int dlclose(@NativeType("void *") long handle){
  if (CHECKS) {
    check(handle);
  }
  return ndlclose(handle);
}
