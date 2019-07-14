/** 
 * Writes a long value to the specified memory address.
 * @param ptr   the memory address to write
 * @param value the value to write
 */
static void putLong(@NativeType("void *") long ptr,@NativeType("int64_t") long value){
  if (CHECKS) {
    check(ptr);
  }
  nputLong(ptr,value);
}
