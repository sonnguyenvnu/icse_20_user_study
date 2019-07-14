/** 
 * ?????????????
 */
private void loadRemoteExtDict(){
  List<String> remoteExtDictFiles=getRemoteExtDictionarys();
  for (  String location : remoteExtDictFiles) {
    logger.info("[Dict Loading] " + location);
    List<String> lists=getRemoteWords(location);
    if (lists == null) {
      logger.error("[Dict Loading] " + location + "????");
      continue;
    }
    for (    String theWord : lists) {
      if (theWord != null && !"".equals(theWord.trim())) {
        logger.info(theWord);
        _MainDict.fillSegment(theWord.trim().toLowerCase().toCharArray());
      }
    }
  }
}
