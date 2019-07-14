/** 
 * Returns a pointer to a function in a bundle’s executable code using the function name as the search key.
 * @param bundle       the bundle to examine
 * @param functionName the name of the function to locate
 */
@NativeType("void *") public static long CFBundleGetFunctionPointerForName(@NativeType("CFBundleRef") long bundle,@NativeType("CFStringRef") long functionName){
  if (CHECKS) {
    check(bundle);
    check(functionName);
  }
  return nCFBundleGetFunctionPointerForName(bundle,functionName);
}
