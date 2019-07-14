/** 
 * Sets the specified value to the  {@code mNumWeights} field of the specified {@code struct}. 
 */
public static void nmNumWeights(long struct,int value){
  UNSAFE.putInt(null,struct + AIBone.MNUMWEIGHTS,value);
}
