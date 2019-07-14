/** 
 * Creates random string whose length is the number of characters specified. Characters are chosen from the provided range.
 */
public String random(int count,final char start,final char end){
  if (count == 0) {
    return StringPool.EMPTY;
  }
  final char[] result=new char[count];
  final int len=end - start + 1;
  while (count-- > 0) {
    result[count]=(char)(rnd.nextInt(len) + start);
  }
  return new String(result);
}
