/** 
 * ????????
 * @param lines ???
 */
public static String generateLineBlank(int lines){
  StringBuilder sb=new StringBuilder();
  for (int i=0; i < lines; i++) {
    sb.append("\n");
  }
  return sb.toString();
}
