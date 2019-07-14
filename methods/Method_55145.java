/** 
 * Returns the address of a symbol. <p>The value of handle specifies what images this function searches for to locate the symbol specified by the symbol parameter. The following table describes the possible values for the handle parameter:</p> <table class=striped> <tr><th>Handle value</th><th>Search scope</th></tr> <tr><td> {@code dlopen} handle</td><td>Image associated with the {@link #dlopen} handle.</td></tr><tr><td> {@link #RTLD_DEFAULT}</td><td>Every dependent library or  {@link #RTLD_GLOBAL}–opened library in the current process, in the order they were loaded.</td></tr> <tr><td> {@link #RTLD_NEXT}</td><td>Dependent libraries that were loaded after the one calling this function. Libraries opened with  {@link #dlopen} are not searched.</td></tr></table> <p>Unlike in the NS... functions, the symbol parameter doesn't require a leading underscore to be part of the symbol name.</p>
 * @param handle a handle obtained by a call to {@link #dlopen}, or a special handle. If the handle was obtained by a call to  {@link #dlopen}, it must not have been closed with a call to  {@link #dlclose}. These are the possible special-handle values:  {@link #RTLD_DEFAULT}, and  {@link #RTLD_NEXT}.
 * @param name   the null-terminated character string containing the C name of the symbol being sought
 */
@NativeType("void *") public static long dlsym(@NativeType("void *") long handle,@NativeType("char const *") CharSequence name){
  if (CHECKS) {
    check(handle);
  }
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nASCII(name,true);
    long nameEncoded=stack.getPointerAddress();
    return ndlsym(handle,nameEncoded);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
