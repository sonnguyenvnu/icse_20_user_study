/** 
 * Returns a combined style from the given styles.
 * @param styles list of separate styles, entries might be null
 * @return combined style or <code>null</code> if no style is given
 */
public static String combine(final String... styles){
  final StringBuilder sb=new StringBuilder();
  for (  final String style : styles) {
    if (style != null) {
      if (sb.length() > 0) {
        sb.append(" ");
      }
      sb.append(style);
    }
  }
  return sb.length() == 0 ? null : sb.toString();
}
