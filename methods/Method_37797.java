/** 
 * Converts an array to string array.
 */
public static String[] toStringArray(double[] array){
  if (array == null) {
    return null;
  }
  String[] result=new String[array.length];
  for (int i=0; i < array.length; i++) {
    result[i]=String.valueOf(array[i]);
  }
  return result;
}
