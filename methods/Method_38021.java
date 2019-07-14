/** 
 * Creates a new string that contains the provided string a number of times.
 */
public static String repeat(final String source,int count){
  StringBand result=new StringBand(count);
  while (count > 0) {
    result.append(source);
    count--;
  }
  return result.toString();
}
