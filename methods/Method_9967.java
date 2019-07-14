/** 
 * Get side breezemoons.
 * @return a list of breezemoons, returns an empty list if not found
 */
public List<JSONObject> getSideBreezemoons(final int avatarViewMode){
  try {
    final BeanManager beanManager=BeanManager.getInstance();
    final BreezemoonRepository breezemoonRepository=beanManager.getReference(BreezemoonRepository.class);
    final Query query=new Query().setPage(1,Symphonys.SIDE_BREEZEMOON_CNT * 2).setPageCount(1).addSort(Keys.OBJECT_ID,SortDirection.DESCENDING);
    List<JSONObject> ret=breezemoonRepository.getList(query);
    final BreezemoonQueryService breezemoonQueryService=beanManager.getReference(BreezemoonQueryService.class);
    breezemoonQueryService.organizeBreezemoons("",ret);
    final Iterator<JSONObject> iterator=ret.iterator();
    while (iterator.hasNext()) {
      final JSONObject bm=iterator.next();
      String content=bm.optString(Breezemoon.BREEZEMOON_CONTENT);
      content=Jsoup.clean(content,Whitelist.none());
      content=StringUtils.trim(content);
      if (StringUtils.isBlank(content)) {
        iterator.remove();
      }
      content=StringUtils.substring(content,0,52);
      bm.put(Breezemoon.BREEZEMOON_CONTENT,content);
    }
    if (ret.size() > Symphonys.SIDE_BREEZEMOON_CNT) {
      ret=ret.subList(0,Symphonys.SIDE_BREEZEMOON_CNT);
    }
    if (UserExt.USER_AVATAR_VIEW_MODE_C_STATIC == avatarViewMode) {
      for (      final JSONObject breezemoon : ret) {
        breezemoon.put(Breezemoon.BREEZEMOON_T_AUTHOR_THUMBNAIL_URL + "48",breezemoon.optString(Breezemoon.BREEZEMOON_T_AUTHOR_THUMBNAIL_URL + "48Static"));
      }
    }
    return ret;
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Get side breezemoons failed",e);
    return Collections.emptyList();
  }
}
