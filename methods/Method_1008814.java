/** 
 * Returns a string consisting of a specific number of concatenated copies of an input string. For example,  {@code repeat("hey", 3)} returns the string {@code "heyheyhey"}.
 * @param string any non-null string
 * @param count the number of times to repeat it; a nonnegative integer
 * @return a string containing {@code string} repeated {@code count} times (the empty string if{@code count} is zero)
 * @throws IllegalArgumentException if {@code count} is negative
 */
public static String repeat(String string,int count){
  checkNotNull(string);
  if (count <= 1) {
    checkArgument(count >= 0,"invalid count: %s",count);
    return (count == 0) ? "" : string;
  }
  final int len=string.length();
  final long longSize=(long)len * (long)count;
  final int size=(int)longSize;
  if (size != longSize) {
    throw new ArrayIndexOutOfBoundsException("Required array size too large: " + longSize);
  }
  final char[] array=new char[size];
  string.getChars(0,len,array,0);
  int n;
  for (n=len; n < size - n; n<<=1) {
    System.arraycopy(array,0,array,n,n);
  }
  System.arraycopy(array,0,array,n,size - n);
  return new String(array);
}
