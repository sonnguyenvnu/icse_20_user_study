/** 
 * same as calling new int[2]; 
 */
public static int[] newArrayOfSize2(){
  if (arrays == null || arrays.length == index) {
    arrays=new int[batchSize][2];
    index=0;
  }
  int[] toReturn=arrays[index];
  arrays[index++]=null;
  return toReturn;
}
