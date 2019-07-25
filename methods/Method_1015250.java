/** 
 * @param n a number
 * @param offset the offset of the bit being tested
 * @return true if the bit is 1, false otherwise
 */
public static boolean test(int n,int offset){
  return (n & (1 << offset)) != 0;
}
