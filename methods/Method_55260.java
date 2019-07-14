/** 
 * Writes a pointer address to the specified memory address.
 * @param ptr   the memory address to write
 * @param value the value to write
 */
static void putAddress(@NativeType("void *") long ptr,@NativeType("intptr_t") long value){
  if (CHECKS) {
    check(ptr);
  }
  nputAddress(ptr,value);
}
