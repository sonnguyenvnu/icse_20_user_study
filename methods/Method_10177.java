/** 
 * Gets HTML for the specified markdown text.
 * @param markdownText the specified markdown text
 * @return HTML
 */
private static String getHTML(final String markdownText){
  final String hash=DigestUtils.md5Hex(markdownText);
  final JSONObject value=MD_CACHE.get(hash);
  if (null == value) {
    return null;
  }
  return value.optString(Common.DATA);
}
