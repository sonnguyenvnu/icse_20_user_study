/** 
 * Gets the article participants (commenters) with the specified article article id and fetch size.
 * @param articleId the specified article id
 * @param fetchSize the specified fetch size
 * @return article participants, for example,      <pre>[ { "oId": "", "articleParticipantName": "", "articleParticipantThumbnailURL": "", "articleParticipantThumbnailUpdateTime": long, "commentId": "" }, .... ] </pre>, returns an empty list if not found
 */
public List<JSONObject> getArticleLatestParticipants(final String articleId,final int fetchSize){
  final Query query=new Query().addSort(Keys.OBJECT_ID,SortDirection.DESCENDING).setFilter(new PropertyFilter(Comment.COMMENT_ON_ARTICLE_ID,FilterOperator.EQUAL,articleId)).select(Keys.OBJECT_ID,Comment.COMMENT_AUTHOR_ID).setPageCount(1).setPage(1,fetchSize);
  final List<JSONObject> ret=new ArrayList<>();
  try {
    final JSONObject result=commentRepository.get(query);
    final List<JSONObject> comments=new ArrayList<>();
    final JSONArray records=result.optJSONArray(Keys.RESULTS);
    for (int i=0; i < records.length(); i++) {
      final JSONObject comment=records.optJSONObject(i);
      boolean exist=false;
      for (      final JSONObject c : comments) {
        if (comment.optString(Comment.COMMENT_AUTHOR_ID).equals(c.optString(Comment.COMMENT_AUTHOR_ID))) {
          exist=true;
          break;
        }
      }
      if (!exist) {
        comments.add(comment);
      }
    }
    for (    final JSONObject comment : comments) {
      final String userId=comment.optString(Comment.COMMENT_AUTHOR_ID);
      final JSONObject commenter=userRepository.get(userId);
      final String email=commenter.optString(User.USER_EMAIL);
      String thumbnailURL=AvatarQueryService.DEFAULT_AVATAR_URL;
      if (!UserExt.COM_BOT_EMAIL.equals(email)) {
        thumbnailURL=avatarQueryService.getAvatarURLByUser(commenter,"48");
      }
      final JSONObject participant=new JSONObject();
      participant.put(Article.ARTICLE_T_PARTICIPANT_NAME,commenter.optString(User.USER_NAME));
      participant.put(Article.ARTICLE_T_PARTICIPANT_THUMBNAIL_URL,thumbnailURL);
      participant.put(Article.ARTICLE_T_PARTICIPANT_URL,commenter.optString(User.USER_URL));
      participant.put(Keys.OBJECT_ID,commenter.optString(Keys.OBJECT_ID));
      participant.put(Comment.COMMENT_T_ID,comment.optString(Keys.OBJECT_ID));
      ret.add(participant);
    }
    return ret;
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets article [" + articleId + "] participants failed",e);
    return Collections.emptyList();
  }
}
