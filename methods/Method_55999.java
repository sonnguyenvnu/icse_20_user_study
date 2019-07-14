/** 
 * Returns a  {@link IntBuffer} view of the {@code addressMode} field. 
 */
@NativeType("CUaddress_mode[3]") public IntBuffer addressMode(){
  return naddressMode(address());
}
