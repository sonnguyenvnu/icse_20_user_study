/** 
 * Writes a double value to the specified memory address.
 * @param ptr   the memory address to write
 * @param value the value to write
 */
static void putDouble(@NativeType("void *") long ptr,double value){
  if (CHECKS) {
    check(ptr);
  }
  nputDouble(ptr,value);
}
