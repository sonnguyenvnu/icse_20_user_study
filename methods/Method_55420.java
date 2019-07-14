/** 
 * Retrieves the  {@code DPI_AWARENESS} value from a {@code DPI_AWARENESS_CONTEXT}.
 * @param value the {@code DPI_AWARENESS_CONTEXT} you want to examine
 * @return the {@code DPI_AWARENESS}. If the provided value is null or invalid, this method will return  {@link #DPI_AWARENESS_INVALID}.
 * @since Windows 10
 */
@NativeType("DPI_AWARENESS") public static int GetAwarenessFromDpiAwarenessContext(@NativeType("DPI_AWARENESS_CONTEXT") long value){
  long __functionAddress=Functions.GetAwarenessFromDpiAwarenessContext;
  if (CHECKS) {
    check(__functionAddress);
    check(value);
  }
  return callPI(value,__functionAddress);
}
