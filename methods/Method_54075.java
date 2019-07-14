/** 
 * Sets the address of the specified  {@link PointerBuffer} to the {@code mTextures} field. 
 */
public AIScene mTextures(@Nullable @NativeType("struct aiTexture **") PointerBuffer value){
  nmTextures(address(),value);
  return this;
}
