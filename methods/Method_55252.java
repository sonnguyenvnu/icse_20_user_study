/** 
 * Reads a float value from the specified memory address.
 * @param ptr the memory address to read
 */
static float getFloat(@NativeType("void *") long ptr){
  if (CHECKS) {
    check(ptr);
  }
  return ngetFloat(ptr);
}
