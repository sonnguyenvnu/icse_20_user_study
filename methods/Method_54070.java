/** 
 * Returns a  {@link PointerBuffer} view of the data pointed to by the {@code mAnimations} field. 
 */
@Nullable @NativeType("struct aiAnimation **") public PointerBuffer mAnimations(){
  return nmAnimations(address());
}
