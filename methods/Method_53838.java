/** 
 * Sets the specified value to the  {@code TellProc} field. 
 */
public AIFile TellProc(@NativeType("aiFileTellProc") AIFileTellProcI value){
  nTellProc(address(),value);
  return this;
}
