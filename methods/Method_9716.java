@Override public void action(final Event<JSONObject> event){
  final JSONObject data=event.getData();
  LOGGER.log(Level.TRACE,"Processing an event [type={0}, data={1}]",event.getType(),data);
  try {
    final JSONObject originalArticle=data.getJSONObject(Article.ARTICLE);
    final JSONObject originalComment=data.getJSONObject(Comment.COMMENT);
    final int commentViewMode=data.optInt(UserExt.USER_COMMENT_VIEW_MODE);
    final String articleId=originalArticle.optString(Keys.OBJECT_ID);
    final String commentId=originalComment.optString(Keys.OBJECT_ID);
    final String originalCmtId=originalComment.optString(Comment.COMMENT_ORIGINAL_COMMENT_ID);
    final String commenterId=originalComment.optString(Comment.COMMENT_AUTHOR_ID);
    final String commentContent=originalComment.optString(Comment.COMMENT_CONTENT);
    final JSONObject commenter=userQueryService.getUser(commenterId);
    final String commenterName=commenter.optString(User.USER_NAME);
    final JSONObject chData=JSONs.clone(originalComment);
    chData.put(Comment.COMMENT_T_COMMENTER,commenter);
    chData.put(Keys.OBJECT_ID,commentId);
    chData.put(Article.ARTICLE_T_ID,articleId);
    chData.put(Article.ARTICLE,originalArticle);
    chData.put(Comment.COMMENT_T_ID,commentId);
    chData.put(Comment.COMMENT_ORIGINAL_COMMENT_ID,originalCmtId);
    String originalCmtAuthorId=null;
    if (StringUtils.isNotBlank(originalCmtId)) {
      final Query numQuery=new Query().setPage(1,Integer.MAX_VALUE).setPageCount(1);
switch (commentViewMode) {
case UserExt.USER_COMMENT_VIEW_MODE_C_TRADITIONAL:
        numQuery.setFilter(CompositeFilterOperator.and(new PropertyFilter(Comment.COMMENT_ON_ARTICLE_ID,FilterOperator.EQUAL,articleId),new PropertyFilter(Keys.OBJECT_ID,FilterOperator.LESS_THAN_OR_EQUAL,originalCmtId))).addSort(Keys.OBJECT_ID,SortDirection.ASCENDING);
      break;
case UserExt.USER_COMMENT_VIEW_MODE_C_REALTIME:
    numQuery.setFilter(CompositeFilterOperator.and(new PropertyFilter(Comment.COMMENT_ON_ARTICLE_ID,FilterOperator.EQUAL,articleId),new PropertyFilter(Keys.OBJECT_ID,FilterOperator.GREATER_THAN_OR_EQUAL,originalCmtId))).addSort(Keys.OBJECT_ID,SortDirection.DESCENDING);
  break;
}
final long num=commentRepository.count(numQuery);
final int page=(int)((num / Symphonys.ARTICLE_COMMENTS_CNT) + 1);
chData.put(Pagination.PAGINATION_CURRENT_PAGE_NUM,page);
final JSONObject originalCmt=commentRepository.get(originalCmtId);
originalCmtAuthorId=originalCmt.optString(Comment.COMMENT_AUTHOR_ID);
final JSONObject originalCmtAuthor=userRepository.get(originalCmtAuthorId);
if (Comment.COMMENT_ANONYMOUS_C_PUBLIC == originalCmt.optInt(Comment.COMMENT_ANONYMOUS)) {
chData.put(Comment.COMMENT_T_ORIGINAL_AUTHOR_THUMBNAIL_URL,avatarQueryService.getAvatarURLByUser(originalCmtAuthor,"20"));
}
 else {
chData.put(Comment.COMMENT_T_ORIGINAL_AUTHOR_THUMBNAIL_URL,avatarQueryService.getDefaultAvatarURL("20"));
}
}
if (Comment.COMMENT_ANONYMOUS_C_PUBLIC == originalComment.optInt(Comment.COMMENT_ANONYMOUS)) {
chData.put(Comment.COMMENT_T_AUTHOR_NAME,commenterName);
chData.put(Comment.COMMENT_T_AUTHOR_THUMBNAIL_URL,avatarQueryService.getAvatarURLByUser(commenter,"48"));
}
 else {
chData.put(Comment.COMMENT_T_AUTHOR_NAME,UserExt.ANONYMOUS_USER_NAME);
chData.put(Comment.COMMENT_T_AUTHOR_THUMBNAIL_URL,avatarQueryService.getDefaultAvatarURL("48"));
}
chData.put(Common.TIME_AGO,langPropsService.get("justNowLabel"));
chData.put(Comment.COMMENT_CREATE_TIME_STR,DateFormatUtils.format(chData.optLong(Comment.COMMENT_CREATE_TIME),"yyyy-MM-dd HH:mm:ss"));
String thankTemplate=langPropsService.get("thankConfirmLabel");
thankTemplate=thankTemplate.replace("{point}",String.valueOf(Symphonys.POINT_THANK_COMMENT)).replace("{user}",commenterName);
chData.put(Comment.COMMENT_T_THANK_LABEL,thankTemplate);
String cc=shortLinkQueryService.linkArticle(commentContent);
cc=Emotions.toAliases(cc);
cc=Emotions.convert(cc);
cc=Markdowns.toHTML(cc);
cc=Markdowns.clean(cc,"");
cc=MP3Players.render(cc);
cc=VideoPlayers.render(cc);
chData.put(Comment.COMMENT_CONTENT,cc);
chData.put(Comment.COMMENT_UA,originalComment.optString(Comment.COMMENT_UA));
ArticleChannel.notifyComment(chData);
final JSONObject articleHeat=new JSONObject();
articleHeat.put(Article.ARTICLE_T_ID,articleId);
articleHeat.put(Common.OPERATION,"+");
ArticleListChannel.notifyHeat(articleHeat);
ArticleChannel.notifyHeat(articleHeat);
final boolean isDiscussion=originalArticle.optInt(Article.ARTICLE_TYPE) == Article.ARTICLE_TYPE_C_DISCUSSION;
final String articleAuthorId=originalArticle.optString(Article.ARTICLE_AUTHOR_ID);
final boolean commenterIsArticleAuthor=articleAuthorId.equals(commenterId);
final Set<String> requisiteAtParticipantsPermissions=new HashSet<>();
requisiteAtParticipantsPermissions.add(Permission.PERMISSION_ID_C_COMMON_AT_PARTICIPANTS);
final boolean hasAtParticipantPerm=roleQueryService.userHasPermissions(commenterId,requisiteAtParticipantsPermissions);
if (hasAtParticipantPerm) {
if (commentContent.contains("@participants ")) {
final List<JSONObject> participants=articleQueryService.getArticleLatestParticipants(articleId,Integer.MAX_VALUE);
int count=participants.size();
if (count < 1) {
  return;
}
count=0;
for (final JSONObject participant : participants) {
  final String participantId=participant.optString(Keys.OBJECT_ID);
  if (participantId.equals(commenterId)) {
    continue;
  }
  count++;
  final JSONObject requestJSONObject=new JSONObject();
  requestJSONObject.put(Notification.NOTIFICATION_USER_ID,participantId);
  requestJSONObject.put(Notification.NOTIFICATION_DATA_ID,commentId);
  notificationMgmtService.addAtNotification(requestJSONObject);
}
final int sum=count * Pointtransfer.TRANSFER_SUM_C_AT_PARTICIPANTS;
if (sum > 0) {
  pointtransferMgmtService.transfer(commenterId,Pointtransfer.ID_C_SYS,Pointtransfer.TRANSFER_TYPE_C_AT_PARTICIPANTS,sum,commentId,System.currentTimeMillis(),"");
}
return;
}
}
final Set<String> atUserNames=userQueryService.getUserNames(commentContent);
atUserNames.remove(commenterName);
final Set<String> watcherIds=new HashSet<>();
final JSONObject followerUsersResult=followQueryService.getArticleWatchers(UserExt.USER_AVATAR_VIEW_MODE_C_ORIGINAL,articleId,1,Integer.MAX_VALUE);
final List<JSONObject> watcherUsers=(List<JSONObject>)followerUsersResult.opt(Keys.RESULTS);
for (final JSONObject watcherUser : watcherUsers) {
final String watcherUserId=watcherUser.optString(Keys.OBJECT_ID);
watcherIds.add(watcherUserId);
}
watcherIds.remove(articleAuthorId);
if (commenterIsArticleAuthor && atUserNames.isEmpty() && watcherIds.isEmpty() && StringUtils.isBlank(originalCmtId)) {
return;
}
if (!commenterIsArticleAuthor) {
final JSONObject requestJSONObject=new JSONObject();
requestJSONObject.put(Notification.NOTIFICATION_USER_ID,articleAuthorId);
requestJSONObject.put(Notification.NOTIFICATION_DATA_ID,commentId);
notificationMgmtService.addCommentedNotification(requestJSONObject);
}
final Set<String> repliedIds=new HashSet<>();
if (StringUtils.isNotBlank(originalCmtId)) {
if (!articleAuthorId.equals(originalCmtAuthorId)) {
final JSONObject requestJSONObject=new JSONObject();
requestJSONObject.put(Notification.NOTIFICATION_USER_ID,originalCmtAuthorId);
requestJSONObject.put(Notification.NOTIFICATION_DATA_ID,commentId);
notificationMgmtService.addReplyNotification(requestJSONObject);
repliedIds.add(originalCmtAuthorId);
}
}
final String articleContent=originalArticle.optString(Article.ARTICLE_CONTENT);
final Set<String> articleContentAtUserNames=userQueryService.getUserNames(articleContent);
final Set<String> requisiteAtUserPermissions=new HashSet<>();
requisiteAtUserPermissions.add(Permission.PERMISSION_ID_C_COMMON_AT_USER);
final boolean hasAtUserPerm=roleQueryService.userHasPermissions(commenterId,requisiteAtUserPermissions);
final Set<String> atIds=new HashSet<>();
if (hasAtUserPerm) {
for (final String atUserName : atUserNames) {
if (isDiscussion && !articleContentAtUserNames.contains(atUserName)) {
  continue;
}
final JSONObject atUser=userQueryService.getUserByName(atUserName);
if (atUser.optString(Keys.OBJECT_ID).equals(articleAuthorId)) {
  continue;
}
final String atUserId=atUser.optString(Keys.OBJECT_ID);
if (repliedIds.contains(atUserId)) {
  continue;
}
final JSONObject requestJSONObject=new JSONObject();
requestJSONObject.put(Notification.NOTIFICATION_USER_ID,atUserId);
requestJSONObject.put(Notification.NOTIFICATION_DATA_ID,commentId);
notificationMgmtService.addAtNotification(requestJSONObject);
atIds.add(atUserId);
}
}
if (64 <= StringUtils.length(commentContent)) {
for (final String userId : watcherIds) {
final JSONObject watcher=userRepository.get(userId);
final String watcherName=watcher.optString(User.USER_NAME);
if ((isDiscussion && !articleContentAtUserNames.contains(watcherName)) || commenterName.equals(watcherName) || repliedIds.contains(userId) || atIds.contains(userId)) {
  continue;
}
if (Comment.COMMENT_VISIBLE_C_AUTHOR == originalComment.optInt(Comment.COMMENT_VISIBLE)) {
  continue;
}
final JSONObject requestJSONObject=new JSONObject();
requestJSONObject.put(Notification.NOTIFICATION_USER_ID,userId);
requestJSONObject.put(Notification.NOTIFICATION_DATA_ID,commentId);
notificationMgmtService.addFollowingArticleCommentNotification(requestJSONObject);
}
}
}
 catch (final Exception e) {
LOGGER.log(Level.ERROR,"Sends the comment notification failed",e);
}
}
