/** 
 * @param arrs arrays
 * @return the concatenated array.
 */
public static byte[] concatenate(List<byte[]> arrs){
  int length=0;
  for (  byte[] arr : arrs) {
    length+=arr.length;
  }
  byte[] result=new byte[length];
  int destPos=0;
  for (  byte[] arr : arrs) {
    System.arraycopy(arr,0,result,destPos,arr.length);
    destPos+=arr.length;
  }
  return result;
}
