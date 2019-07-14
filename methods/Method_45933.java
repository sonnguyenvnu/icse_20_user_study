/** 
 * Override blacklist with override string.
 * @param originList Origin black list
 * @param overrideStr The override string
 */
static void overrideBlackList(List<String> originList,String overrideStr){
  List<String> adds=new ArrayList<String>();
  String[] overrideItems=StringUtils.splitWithCommaOrSemicolon(overrideStr);
  for (  String overrideItem : overrideItems) {
    if (StringUtils.isNotBlank(overrideItem)) {
      if (overrideItem.startsWith("!") || overrideItem.startsWith("-")) {
        overrideItem=overrideItem.substring(1);
        if ("*".equals(overrideItem) || "default".equals(overrideItem)) {
          originList.clear();
        }
 else {
          originList.remove(overrideItem);
        }
      }
 else {
        if (!originList.contains(overrideItem)) {
          adds.add(overrideItem);
        }
      }
    }
  }
  if (adds.size() > 0) {
    originList.addAll(adds);
  }
}
