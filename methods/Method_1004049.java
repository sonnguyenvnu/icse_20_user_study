/** 
 * @return String containing all available command names
 */
public static String names(){
  final StringBuilder sb=new StringBuilder();
  for (  final Command c : get()) {
    if (sb.length() > 0) {
      sb.append('|');
    }
    sb.append(c.name());
  }
  return sb.toString();
}
