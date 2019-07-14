/** 
 * Unsafe version of  {@link #mColors}. 
 */
public static PointerBuffer nmColors(long struct){
  return memPointerBuffer(struct + AIMesh.MCOLORS,Assimp.AI_MAX_NUMBER_OF_COLOR_SETS);
}
