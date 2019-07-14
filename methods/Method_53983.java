/** 
 * Returns a  {@link PointerBuffer} view of the data pointed to by the {@code mBones} field. 
 */
@Nullable @NativeType("struct aiBone **") public PointerBuffer mBones(){
  return nmBones(address());
}
