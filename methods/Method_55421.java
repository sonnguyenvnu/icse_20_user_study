/** 
 * Gets the  {@code DPI_AWARENESS_CONTEXT} for the current thread.<p>If  {@link #SetThreadDpiAwarenessContext} was never called for this thread, then the return value will equal the default {@code DPI_AWARENESS_CONTEXT} for theprocess.</p>
 * @return the current {@code DPI_AWARENESS_CONTEXT} for the thread.
 * @since Windows 10
 */
@NativeType("DPI_AWARENESS_CONTEXT") public static long GetThreadDpiAwarenessContext(){
  long __functionAddress=Functions.GetThreadDpiAwarenessContext;
  if (CHECKS) {
    check(__functionAddress);
  }
  return callP(__functionAddress);
}
