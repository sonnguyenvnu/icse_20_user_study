/** 
 * Sets the address of the specified  {@link PointerBuffer} to the {@code mMorphMeshChannels} field. 
 */
public AIAnimation mMorphMeshChannels(@Nullable @NativeType("struct aiMeshMorphAnim **") PointerBuffer value){
  nmMorphMeshChannels(address(),value);
  return this;
}
