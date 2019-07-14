/** 
 * Like  {@link #get}, but returns  {@code null} if {@code functionPointer} is {@code NULL}. 
 */
@Nullable public static <T extends CallbackI>T getSafe(long functionPointer){
  return functionPointer == NULL ? null : get(functionPointer);
}
