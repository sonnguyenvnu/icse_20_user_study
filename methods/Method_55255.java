/** 
 * Writes a short value to the specified memory address.
 * @param ptr   the memory address to write
 * @param value the value to write
 */
static void putShort(@NativeType("void *") long ptr,@NativeType("int16_t") short value){
  if (CHECKS) {
    check(ptr);
  }
  nputShort(ptr,value);
}
