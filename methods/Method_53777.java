/** 
 * Unsafe version of  {@link #mColors(int) mColors}. 
 */
@Nullable public static AIColor4D.Buffer nmColors(long struct,int index){
  return AIColor4D.createSafe(memGetAddress(struct + AIAnimMesh.MCOLORS + check(index,Assimp.AI_MAX_NUMBER_OF_COLOR_SETS) * POINTER_SIZE),nmNumVertices(struct));
}
