/** 
 * Converts to object array.
 */
public static Float[] valuesOf(float[] array){
  Float[] dest=new Float[array.length];
  for (int i=0; i < array.length; i++) {
    dest[i]=Float.valueOf(array[i]);
  }
  return dest;
}
