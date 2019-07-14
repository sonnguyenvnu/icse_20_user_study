/** 
 * Returns the value of the  {@code ReadProc} field. 
 */
@NativeType("aiFileReadProc") public AIFileReadProc ReadProc(){
  return nReadProc(address());
}
