/** 
 * Gets and sets the current C locale. <p>The  {@code setlocale} function installs the specified system locale or its portion as the new C locale. The modifications remain in effect andinfluences the execution of all locale-sensitive C library functions until the next call to  {@code setlocale}. If  {@code locale} is a null pointer,{@code setlocale} queries the current C locale without modifying it.</p>
 * @param category the locale category identifier, may by null. One of:<br><table><tr><td>{@link #LC_ALL}</td><td> {@link #LC_COLLATE}</td><td> {@link #LC_CTYPE}</td><td> {@link #LC_MONETARY}</td><td> {@link #LC_NUMERIC}</td><td> {@link #LC_TIME}</td></tr></table>
 * @param locale   system-specific locale identifier. Can be "" for the user-preferred locale or "C" for the minimal locale.
 * @return a pointer to a narrow null-terminated string identifying the C locale after applying the changes, if any, or null pointer on failure.<p>A copy of the returned string along with the category used in this call to  {@code setlocale} may be used later in the program to restore the localeback to the state at the end of this call.</p>
 */
@Nullable @NativeType("char *") public static String setlocale(int category,@NativeType("char const *") CharSequence locale){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nASCII(locale,true);
    long localeEncoded=stack.getPointerAddress();
    long __result=nsetlocale(category,localeEncoded);
    return memASCIISafe(__result);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
