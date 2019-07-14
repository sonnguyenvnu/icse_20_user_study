/** 
 * Returns a  {@link PointerBuffer} view of the data pointed to by the {@code mCameras} field. 
 */
@Nullable @NativeType("struct aiCamera **") public PointerBuffer mCameras(){
  return nmCameras(address());
}
