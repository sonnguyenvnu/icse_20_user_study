/** 
 * Writes an int value to the specified memory address.
 * @param ptr   the memory address to write
 * @param value the value to write
 */
static void putInt(@NativeType("void *") long ptr,@NativeType("int32_t") int value){
  if (CHECKS) {
    check(ptr);
  }
  nputInt(ptr,value);
}
