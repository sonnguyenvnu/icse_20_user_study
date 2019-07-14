/** 
 * Gets a comment's revisions.
 * @param viewer    the specified viewer
 * @param commentId the specified comment id
 * @return comment revisions, returns an empty list if not found
 */
public List<JSONObject> getCommentRevisions(final JSONObject viewer,final String commentId){
  List<JSONObject> ret=new ArrayList<>();
  try {
    final JSONObject comment=commentRepository.get(commentId);
    if (null == comment || Comment.COMMENT_STATUS_C_VALID != comment.optInt(Comment.COMMENT_STATUS)) {
      return ret;
    }
    if (Comment.COMMENT_VISIBLE_C_AUTHOR == comment.optInt(Comment.COMMENT_VISIBLE)) {
      final JSONObject article=articleRepository.get(comment.optString(Comment.COMMENT_ON_ARTICLE_ID));
      if (null == article) {
        return ret;
      }
      final String viewerId=viewer.optString(Keys.OBJECT_ID);
      final String commentAuthorId=comment.optString(Comment.COMMENT_AUTHOR_ID);
      final String articleAuthorId=article.optString(Article.ARTICLE_AUTHOR_ID);
      if (!viewerId.equals(commentAuthorId) && !viewerId.equals(articleAuthorId)) {
        return ret;
      }
    }
    final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Revision.REVISION_DATA_ID,FilterOperator.EQUAL,commentId),new PropertyFilter(Revision.REVISION_DATA_TYPE,FilterOperator.EQUAL,Revision.DATA_TYPE_C_COMMENT))).addSort(Keys.OBJECT_ID,SortDirection.ASCENDING);
    ret=revisionRepository.getList(query);
    if (ret.isEmpty()) {
      return ret;
    }
    for (    final JSONObject rev : ret) {
      final JSONObject data=new JSONObject(rev.optString(Revision.REVISION_DATA));
      String commentContent=data.optString(Comment.COMMENT_CONTENT);
      commentContent=commentContent.replace("\n","_esc_br_");
      commentContent=Markdowns.clean(commentContent,"");
      commentContent=commentContent.replace("_esc_br_","\n");
      data.put(Comment.COMMENT_CONTENT,commentContent);
      rev.put(Revision.REVISION_DATA,data);
    }
    final JSONObject latestRev=ret.get(ret.size() - 1);
    final JSONObject latestRevData=latestRev.optJSONObject(Revision.REVISION_DATA);
    final String latestRevContent=latestRevData.optString(Comment.COMMENT_CONTENT);
    String currentContent=comment.optString(Comment.COMMENT_CONTENT);
    final boolean contentChanged=!latestRevContent.replaceAll("\\s+","").equals(currentContent.replaceAll("\\s+",""));
    if (contentChanged) {
      final JSONObject appendRev=new JSONObject();
      final JSONObject appendRevData=new JSONObject();
      appendRev.put(Revision.REVISION_DATA,appendRevData);
      currentContent=currentContent.replace("\n","_esc_br_");
      currentContent=Markdowns.clean(currentContent,"");
      currentContent=currentContent.replace("_esc_br_","\n");
      appendRevData.put(Comment.COMMENT_CONTENT,currentContent);
      ret.add(appendRev);
    }
    return ret;
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Gets comment revisions failed",e);
    return Collections.emptyList();
  }
}
