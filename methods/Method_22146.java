/** 
 * ???????????????.
 * @param itemsString ??????
 * @return ?????
 */
public static List<Integer> toItemList(final String itemsString){
  if (Strings.isNullOrEmpty(itemsString)) {
    return Collections.emptyList();
  }
  String[] items=itemsString.split(DELIMITER);
  List<Integer> result=new ArrayList<>(items.length);
  for (  String each : items) {
    int item=Integer.parseInt(each);
    if (!result.contains(item)) {
      result.add(item);
    }
  }
  return result;
}
