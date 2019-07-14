/** 
 * Creates a  {@code AIFileReadProc} instance from the specified function pointer.
 * @return the new {@code AIFileReadProc}
 */
public static AIFileReadProc create(long functionPointer){
  AIFileReadProcI instance=Callback.get(functionPointer);
  return instance instanceof AIFileReadProc ? (AIFileReadProc)instance : new Container(functionPointer,instance);
}
