/** 
 * Replace some whitespace characters so they are visually apparent.
 * @param o
 * @return String
 */
public static String escapeWhitespace(Object o){
  if (o == null) {
    return null;
  }
  String s=String.valueOf(o);
  s=s.replace("\n","\\n");
  s=s.replace("\r","\\r");
  s=s.replace("\t","\\t");
  return s;
}
