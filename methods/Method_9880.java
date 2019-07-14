/** 
 * Gets a link with the specified address.
 * @param addr the specified address
 * @return a link, returns {@code null} if not found
 */
public JSONObject getLink(final String addr){
  final String hash=DigestUtils.sha1Hex(addr);
  final Query query=new Query().setFilter(new PropertyFilter(Link.LINK_ADDR_HASH,FilterOperator.EQUAL,hash)).setPageCount(1).setPage(1,1);
  try {
    final JSONObject result=get(query);
    final JSONArray links=result.optJSONArray(Keys.RESULTS);
    if (0 == links.length()) {
      return null;
    }
    return links.optJSONObject(0);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Gets link by address [addr=" + addr + ", hash=" + hash + "] failed",e);
    return null;
  }
}
