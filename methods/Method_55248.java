/** 
 * Stores a new value in a  {@code __weak} variable.<p>This function is typically used anywhere a  {@code __weak} variable is the target of an assignment.</p>
 * @param location the address of the weak pointer
 * @param obj      the new object you want the weak pointer to now point to
 * @return the value stored in location (that is, {@code obj})
 */
@NativeType("id") public static long objc_storeWeak(@NativeType("id *") PointerBuffer location,@NativeType("id") long obj){
  if (CHECKS) {
    check(location,1);
  }
  return nobjc_storeWeak(memAddress(location),obj);
}
