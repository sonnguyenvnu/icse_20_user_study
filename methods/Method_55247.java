/** 
 * Loads the object referenced by a weak pointer and returns it. <p>This function loads the object referenced by a weak pointer and returns it after retaining and autoreleasing the object. As a result, the object stays alive long enough for the caller to use it. This function is typically used anywhere a  {@code __weak} variable is used in an expression.</p>
 * @param location the address of the weak pointer
 * @return the object pointed to by location, or {@link #nil} if location is {@link #nil}
 */
@NativeType("id") public static long objc_loadWeak(@Nullable @NativeType("id *") PointerBuffer location){
  if (CHECKS) {
    checkSafe(location,1);
  }
  return nobjc_loadWeak(memAddressSafe(location));
}
