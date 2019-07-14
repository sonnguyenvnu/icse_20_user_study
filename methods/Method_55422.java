/** 
 * Set the DPI awareness for the current thread to the provided value.
 * @param dpiContext the DPI awareness value to set
 * @return The old {@code DPI_AWARENESS_CONTEXT} for the thread. If the {@code dpiContext} is invalid, the thread will not be updated and the return value will be{@code NULL}. You can use this value to restore the old  {@code DPI_AWARENESS_CONTEXT} after overriding it with a predefined value.
 * @since Windows 10
 */
@NativeType("DPI_AWARENESS_CONTEXT") public static long SetThreadDpiAwarenessContext(@NativeType("DPI_AWARENESS_CONTEXT") long dpiContext){
  long __functionAddress=Functions.SetThreadDpiAwarenessContext;
  if (CHECKS) {
    check(__functionAddress);
    check(dpiContext);
  }
  return callPP(dpiContext,__functionAddress);
}
