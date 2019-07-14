/** 
 * Reads an int value from the specified memory address.
 * @param ptr the memory address to read
 */
@NativeType("int32_t") static int getInt(@NativeType("void *") long ptr){
  if (CHECKS) {
    check(ptr);
  }
  return ngetInt(ptr);
}
