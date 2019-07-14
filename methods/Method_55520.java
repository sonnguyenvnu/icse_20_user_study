/** 
 * Calls  {@link #loadNative(Class,String)} using {@code Library.class} as the context parameter. 
 */
public static SharedLibrary loadNative(String name){
  return loadNative(Library.class,name);
}
