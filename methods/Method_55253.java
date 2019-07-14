/** 
 * Reads a double value from the specified memory address.
 * @param ptr the memory address to read
 */
static double getDouble(@NativeType("void *") long ptr){
  if (CHECKS) {
    check(ptr);
  }
  return ngetDouble(ptr);
}
