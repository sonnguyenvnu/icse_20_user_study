/** 
 * Unsafe version of  {@link #mTextureCoords(int,AIVector3D.Buffer) mTextureCoords}. 
 */
public static void nmTextureCoords(long struct,int index,@Nullable AIVector3D.Buffer value){
  memPutAddress(struct + AIMesh.MTEXTURECOORDS + check(index,Assimp.AI_MAX_NUMBER_OF_TEXTURECOORDS) * POINTER_SIZE,memAddressSafe(value));
}
