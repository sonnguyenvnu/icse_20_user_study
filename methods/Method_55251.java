/** 
 * Reads a long value from the specified memory address.
 * @param ptr the memory address to read
 */
@NativeType("int64_t") static long getLong(@NativeType("void *") long ptr){
  if (CHECKS) {
    check(ptr);
  }
  return ngetLong(ptr);
}
