/** 
 * Puts the specified HTML into cache.
 * @param markdownText the specified markdown text
 * @param html         the specified HTML
 */
private static void putHTML(final String markdownText,final String html){
  final String hash=DigestUtils.md5Hex(markdownText);
  final JSONObject value=new JSONObject();
  value.put(Common.DATA,html);
  MD_CACHE.put(hash,value);
}
