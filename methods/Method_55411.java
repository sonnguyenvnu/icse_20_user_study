/** 
 * Loads the specified icon resource from the executable (.exe) file associated with an application instance.
 * @param instance a handle to an instance of the module whose executable file contains the icon to be loaded. This parameter must be {@code NULL} when a standard icon isbeing loaded.
 * @param iconName the name of the icon resource to be loaded or one of:<br><table><tr><td>{@link #IDI_APPLICATION}</td><td> {@link #IDI_HAND}</td><td> {@link #IDI_QUESTION}</td><td> {@link #IDI_EXCLAMATION}</td><td> {@link #IDI_ASTERISK}</td><td> {@link #IDI_WINLOGO}</td></tr><tr><td> {@link #IDI_SHIELD}</td><td> {@link #IDI_WARNING}</td><td> {@link #IDI_ERROR}</td><td> {@link #IDI_INFORMATION}</td></tr></table>
 */
@NativeType("HICON") public static long LoadIcon(@NativeType("HINSTANCE") long instance,@NativeType("LPCTSTR") CharSequence iconName){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nUTF16(iconName,true);
    long iconNameEncoded=stack.getPointerAddress();
    return nLoadIcon(instance,iconNameEncoded);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
