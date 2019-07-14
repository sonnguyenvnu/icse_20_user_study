/** 
 * Returns a  {@link PointerBuffer} view of the data pointed to by the {@code mTextures} field. 
 */
@Nullable @NativeType("struct aiTexture **") public PointerBuffer mTextures(){
  return nmTextures(address());
}
