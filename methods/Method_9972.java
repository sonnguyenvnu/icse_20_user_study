/** 
 * Organizes the specified breezemoons with the specified avatar view mode and current user id.
 * @param currentUserId the specified current user id
 * @param breezemoons   the specified breezemoons
 */
public void organizeBreezemoons(final String currentUserId,final List<JSONObject> breezemoons){
  try {
    final Iterator<JSONObject> iterator=breezemoons.iterator();
    while (iterator.hasNext()) {
      final JSONObject bm=iterator.next();
      final String authorId=bm.optString(Breezemoon.BREEZEMOON_AUTHOR_ID);
      final JSONObject author=userRepository.get(authorId);
      if (UserExt.USER_XXX_STATUS_C_PRIVATE == author.optInt(UserExt.USER_BREEZEMOON_STATUS) && !StringUtils.equals(currentUserId,authorId) && !"admin".equals(currentUserId)) {
        iterator.remove();
        continue;
      }
      bm.put(Breezemoon.BREEZEMOON_T_AUTHOR_NAME,author.optString(User.USER_NAME));
      bm.put(Breezemoon.BREEZEMOON_T_AUTHOR_THUMBNAIL_URL + "48",avatarQueryService.getAvatarURLByUser(author,"48"));
      final long time=bm.optLong(Breezemoon.BREEZEMOON_CREATED);
      bm.put(Common.TIME_AGO,Times.getTimeAgo(time,Locales.getLocale()));
      bm.put(Breezemoon.BREEZEMOON_T_CREATE_TIME,new Date(time));
      String content=bm.optString(Breezemoon.BREEZEMOON_CONTENT);
      content=shortLinkQueryService.linkArticle(content);
      content=Emotions.convert(content);
      content=Markdowns.toHTML(content);
      content=Markdowns.clean(content,"");
      content=Images.qiniuImgProcessing(content);
      bm.put(Breezemoon.BREEZEMOON_CONTENT,content);
    }
  }
 catch (  final Exception e) {
  }
}
