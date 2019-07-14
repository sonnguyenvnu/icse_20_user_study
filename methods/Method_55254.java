/** 
 * Writes a byte value to the specified memory address.
 * @param ptr   the memory address to write
 * @param value the value to write
 */
static void putByte(@NativeType("void *") long ptr,@NativeType("int8_t") byte value){
  if (CHECKS) {
    check(ptr);
  }
  nputByte(ptr,value);
}
