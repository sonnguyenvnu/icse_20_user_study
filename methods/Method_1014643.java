/** 
 * ??????????????
 * @param template
 * @param datas
 */
@Deprecated public static void debug(XWPFTemplate template,Map<String,Object> datas){
  List<ElementTemplate> all=template.getElementTemplates();
  LOGGER.debug("Template tag number is:{}",(null == all ? 0 : all.size()));
  if ((all == null || all.isEmpty()) && (null == datas || datas.isEmpty())) {
    LOGGER.debug("No template gramer find and no render data find");
    return;
  }
  Set<String> tagtKeys=new HashSet<String>();
  for (  ElementTemplate ele : all) {
    LOGGER.debug("Parse the tag?{}",ele.getTagName());
    tagtKeys.add(ele.getTagName());
  }
  Set<String> keySet=datas.keySet();
  HashSet<String> copySet=new HashSet<String>(keySet);
  copySet.removeAll(tagtKeys);
  Iterator<String> iterator=copySet.iterator();
  while (iterator.hasNext()) {
    String key=iterator.next();
    LOGGER.warn("Cannot find the gramer tag in template:" + key);
  }
  tagtKeys.removeAll(keySet);
  iterator=tagtKeys.iterator();
  while (iterator.hasNext()) {
    String key=iterator.next();
    LOGGER.warn("Cannot find the feild in java Map or Object:" + key);
  }
}
