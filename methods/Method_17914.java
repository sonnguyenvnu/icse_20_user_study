/** 
 * @param keyParts a list of objects that will be concatenated to form another component's key
 * @return a key formed by concatenating the key parts delimited by a separator.
 */
public static String getKeyWithSeparator(Object... keyParts){
  final StringBuilder sb=new StringBuilder();
  sb.append(keyParts[0]);
  for (int i=1; i < keyParts.length; i++) {
    sb.append(',').append(keyParts[i]);
  }
  return sb.toString();
}
