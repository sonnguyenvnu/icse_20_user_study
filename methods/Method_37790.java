/** 
 * Converts to primitive array.
 */
public static float[] values(Float[] array){
  float[] dest=new float[array.length];
  for (int i=0; i < array.length; i++) {
    Float v=array[i];
    if (v != null) {
      dest[i]=v.floatValue();
    }
  }
  return dest;
}
