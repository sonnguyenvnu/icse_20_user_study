/** 
 * Unsafe version of  {@link #mNumValuesAndWeights}. 
 */
public static int nmNumValuesAndWeights(long struct){
  return UNSAFE.getInt(null,struct + AIMeshMorphKey.MNUMVALUESANDWEIGHTS);
}
