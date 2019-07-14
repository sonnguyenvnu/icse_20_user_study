/** 
 * Ensures that the specified array is null-terminated. 
 */
public static void checkNT(int[] buf){
  checkBuffer(buf.length,1);
  assertNT(buf[buf.length - 1] == 0);
}
