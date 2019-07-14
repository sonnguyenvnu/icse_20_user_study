/** 
 * Reads a short value from the specified memory address.
 * @param ptr the memory address to read
 */
@NativeType("int16_t") static short getShort(@NativeType("void *") long ptr){
  if (CHECKS) {
    check(ptr);
  }
  return ngetShort(ptr);
}
