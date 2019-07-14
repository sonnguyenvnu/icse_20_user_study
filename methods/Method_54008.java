/** 
 * Unsafe version of  {@link #mColors(PointerBuffer) mColors}. 
 */
public static void nmColors(long struct,PointerBuffer value){
  if (CHECKS) {
    checkGT(value,Assimp.AI_MAX_NUMBER_OF_COLOR_SETS);
  }
  memCopy(memAddress(value),struct + AIMesh.MCOLORS,value.remaining() * POINTER_SIZE);
}
