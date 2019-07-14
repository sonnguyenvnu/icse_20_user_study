/** 
 * Uses the head tags.
 * @param tagStr the specified tags
 * @param num    the specified used number
 * @return head tags
 */
public static String useHead(final String tagStr,final int num){
  final String[] tags=tagStr.split(",");
  if (tags.length <= num) {
    return tagStr;
  }
  final StringBuilder sb=new StringBuilder();
  for (int i=0; i < num; i++) {
    sb.append(tags[i]).append(",");
  }
  sb.deleteCharAt(sb.length() - 1);
  return sb.toString();
}
