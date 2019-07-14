/** 
 * Returns a  {@link PointerBuffer} view of the data pointed to by the {@code mMaterials} field. 
 */
@Nullable @NativeType("struct aiMaterial **") public PointerBuffer mMaterials(){
  return nmMaterials(address());
}
