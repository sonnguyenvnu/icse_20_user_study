/** 
 * Converts separated string value to CamelCase.
 */
public static String toCamelCase(final String input,final boolean firstCharUppercase,final char separator){
  final int length=input.length();
  final StringBuilder sb=new StringBuilder(length);
  boolean upperCase=firstCharUppercase;
  for (int i=0; i < length; i++) {
    final char ch=input.charAt(i);
    if (ch == separator) {
      upperCase=true;
    }
 else     if (upperCase) {
      sb.append(Character.toUpperCase(ch));
      upperCase=false;
    }
 else {
      sb.append(ch);
    }
  }
  return sb.toString();
}
