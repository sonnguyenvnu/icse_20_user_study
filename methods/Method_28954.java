/** 
 * ???????(?????)
 * @param list
 * @param separator
 * @return
 */
public static String simpleListJoinToStrWithSeparator(List<String> list,String separator){
  if (list == null || list.isEmpty()) {
    return "";
  }
  StringBuilder finalEmailStr=new StringBuilder();
  for (int i=0; i < list.size(); i++) {
    if (i != 0) {
      finalEmailStr.append(separator);
    }
    finalEmailStr.append(list.get(i));
  }
  return finalEmailStr.toString();
}
