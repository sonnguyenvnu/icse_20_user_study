/** 
 * Writes a float value to the specified memory address.
 * @param ptr   the memory address to write
 * @param value the value to write
 */
static void putFloat(@NativeType("void *") long ptr,float value){
  if (CHECKS) {
    check(ptr);
  }
  nputFloat(ptr,value);
}
