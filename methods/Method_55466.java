/** 
 * Creates a native function that delegates to the specified instance when called. <p>The native function uses the default calling convention.</p>
 * @param signature the {@code dyncall} function signature
 * @param instance  the callback instance
 * @return the dynamically generated native function
 */
static long create(String signature,Object instance){
  long funcptr=getNativeFunction(signature.charAt(signature.length() - 1));
  long handle=dcbNewCallback(signature,funcptr,NewGlobalRef(instance));
  if (handle == NULL) {
    throw new IllegalStateException("Failed to create the DCCallback object");
  }
  if (DEBUG_ALLOCATOR) {
    MemoryManage.DebugAllocator.track(handle,2L * POINTER_SIZE);
  }
  return handle;
}
