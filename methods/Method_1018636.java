/** 
 * @param array values to sum
 * @return sum of values in array
 */
public static int sum(int[] array){
  int count=0;
  for (  int a : array) {
    count+=a;
  }
  return count;
}
