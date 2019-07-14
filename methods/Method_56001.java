/** 
 * Returns the value of the  {@code filterMode} field. 
 */
@NativeType("CUfilter_mode") public int filterMode(){
  return nfilterMode(address());
}
