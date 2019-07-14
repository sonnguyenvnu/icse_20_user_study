/** 
 * Indicates whether  {@code c} is one of the twenty-six uppercase ASCII alphabetic charactersbetween  {@code 'A'} and {@code 'Z'} inclusive. All others (including non-ASCII characters)return  {@code false}.
 */
public static boolean isUpperCase(char c){
  return (c >= 'A') && (c <= 'Z');
}
