/** 
 * Get top links with the specified size.
 * @param size the specified size
 * @return links, returns an empty list if not found
 */
public List<JSONObject> getTopLink(final int size){
  final List<JSONObject> ret=new ArrayList<>();
  try {
    final List<JSONObject> links=linkRepository.select("SELECT\n" + "\t*\n" + "FROM\n" + "\t`symphony_link`\n" + "WHERE\n" + "\tlinkPingErrCnt / linkPingCnt < 0.1\n" + "AND linkTitle != \"\"\n" + "AND linkAddr NOT LIKE \"%baidu.com%\" \n" + "AND linkAddr NOT LIKE \"%weiyun.com%\"\n" + "ORDER BY\n" + "\tlinkClickCnt DESC\n" + "LIMIT ?",size);
    ret.addAll(links);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Get top links failed",e);
  }
  return ret;
}
