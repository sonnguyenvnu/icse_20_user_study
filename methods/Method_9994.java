/** 
 * Processes the specified comment content. <ul> <li>Generates &#64;username home URL</li> <li>Markdowns</li> <li>Blocks comment if need</li> <li>Generates emotion images</li> <li>Generates article link with article id</li> </ul>
 * @param comment the specified comment, for example,"commentContent": "", ...., "commenter": {}
 */
private void processCommentContent(final JSONObject comment){
  final JSONObject commenter=comment.optJSONObject(Comment.COMMENT_T_COMMENTER);
  if (Comment.COMMENT_STATUS_C_INVALID == comment.optInt(Comment.COMMENT_STATUS) || UserExt.USER_STATUS_C_INVALID == commenter.optInt(UserExt.USER_STATUS)) {
    comment.put(Comment.COMMENT_CONTENT,langPropsService.get("commentContentBlockLabel"));
    return;
  }
  String commentContent=comment.optString(Comment.COMMENT_CONTENT);
  commentContent=shortLinkQueryService.linkArticle(commentContent);
  commentContent=Emotions.convert(commentContent);
  commentContent=Markdowns.toHTML(commentContent);
  commentContent=Markdowns.clean(commentContent,"");
  commentContent=MP3Players.render(commentContent);
  commentContent=VideoPlayers.render(commentContent);
  comment.put(Comment.COMMENT_CONTENT,commentContent);
}
