/** 
 * Unsafe version of  {@link #mColors(int,AIColor4D.Buffer) mColors}. 
 */
public static void nmColors(long struct,int index,@Nullable AIColor4D.Buffer value){
  memPutAddress(struct + AIMesh.MCOLORS + check(index,Assimp.AI_MAX_NUMBER_OF_COLOR_SETS) * POINTER_SIZE,memAddressSafe(value));
}
