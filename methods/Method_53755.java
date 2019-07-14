/** 
 * Returns a  {@link PointerBuffer} view of the data pointed to by the {@code mMorphMeshChannels} field. 
 */
@Nullable @NativeType("struct aiMeshMorphAnim **") public PointerBuffer mMorphMeshChannels(){
  return nmMorphMeshChannels(address());
}
