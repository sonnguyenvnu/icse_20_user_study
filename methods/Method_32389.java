/** 
 * Appends the separator if necessary.
 * @param bld  the builder
 * @param extended  whether to append the separator
 * @param sep  the separator
 * @since 1.1
 */
private static void appendSeparator(DateTimeFormatterBuilder bld,boolean extended){
  if (extended) {
    bld.appendLiteral('-');
  }
}
