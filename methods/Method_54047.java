/** 
 * Returns a  {@link AINode} view of the struct pointed to by the {@code mParent} field. 
 */
@Nullable @NativeType("struct aiNode *") public AINode mParent(){
  return nmParent(address());
}
