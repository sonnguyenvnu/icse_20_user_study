/** 
 * Returns the size in bytes of the JVMS exception_table corresponding to the Handler list that begins with the given element. <i>This includes the exception_table_length field.</i>
 * @param firstHandler the beginning of a Handler list. May be {@literal null}.
 * @return the size in bytes of the exception_table_length and exception_table structures.
 */
static int getExceptionTableSize(final Handler firstHandler){
  return 2 + 8 * getExceptionTableLength(firstHandler);
}
