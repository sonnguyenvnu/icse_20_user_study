/** 
 * Creates a  {@code AIFileTellProc} instance from the specified function pointer.
 * @return the new {@code AIFileTellProc}
 */
public static AIFileTellProc create(long functionPointer){
  AIFileTellProcI instance=Callback.get(functionPointer);
  return instance instanceof AIFileTellProc ? (AIFileTellProc)instance : new Container(functionPointer,instance);
}
