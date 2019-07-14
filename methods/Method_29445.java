/** 
 */
public static String convertToCamelCaseString(String inputString,boolean firstCharacterUppercase){
  if (null == inputString) {
    return null;
  }
  StringBuilder sb=new StringBuilder();
  boolean nextUpperCase=false;
  for (int i=0; i < inputString.length(); i++) {
    char c=inputString.charAt(i);
switch (c) {
case '_':
case '-':
case '@':
case '$':
case '#':
case ' ':
case '/':
case '&':
      if (sb.length() > 0) {
        nextUpperCase=true;
      }
    break;
default :
  if (nextUpperCase) {
    sb.append(Character.toUpperCase(c));
    nextUpperCase=false;
  }
 else {
    sb.append(c);
  }
break;
}
}
if (firstCharacterUppercase) {
sb.setCharAt(0,Character.toUpperCase(sb.charAt(0)));
}
 else {
sb.setCharAt(0,Character.toLowerCase(sb.charAt(0)));
}
return sb.toString();
}
