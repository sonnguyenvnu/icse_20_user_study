/** 
 * Gets 'point' type notifications with the specified user id, current page number and page size.
 * @param userId         the specified user id
 * @param currentPageNum the specified page number
 * @param pageSize       the specified page size
 * @return result json object, for example,      <pre>{ "paginationRecordCount": int, "rslts": java.util.List[{ "oId": "", // notification record id "description": "", "hasRead": boolean }, ....] } </pre>
 */
public JSONObject getPointNotifications(final String userId,final int currentPageNum,final int pageSize){
  final JSONObject ret=new JSONObject();
  final List<JSONObject> rslts=new ArrayList<>();
  ret.put(Keys.RESULTS,(Object)rslts);
  final List<Filter> filters=new ArrayList<>();
  filters.add(new PropertyFilter(Notification.NOTIFICATION_USER_ID,FilterOperator.EQUAL,userId));
  final List<Filter> subFilters=new ArrayList<>();
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_POINT_ARTICLE_REWARD));
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_POINT_ARTICLE_THANK));
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_POINT_CHARGE));
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_POINT_EXCHANGE));
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_ABUSE_POINT_DEDUCT));
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_POINT_COMMENT_THANK));
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_POINT_COMMENT_ACCEPT));
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_POINT_TRANSFER));
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_INVITECODE_USED));
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_INVITATION_LINK_USED));
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_POINT_PERFECT_ARTICLE));
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_POINT_REPORT_HANDLED));
  filters.add(new CompositeFilter(CompositeFilterOperator.OR,subFilters));
  final Query query=new Query().setPage(currentPageNum,pageSize).setFilter(new CompositeFilter(CompositeFilterOperator.AND,filters)).addSort(Notification.NOTIFICATION_HAS_READ,SortDirection.ASCENDING).addSort(Keys.OBJECT_ID,SortDirection.DESCENDING);
  try {
    final JSONObject queryResult=notificationRepository.get(query);
    final JSONArray results=queryResult.optJSONArray(Keys.RESULTS);
    ret.put(Pagination.PAGINATION_RECORD_COUNT,queryResult.optJSONObject(Pagination.PAGINATION).optInt(Pagination.PAGINATION_RECORD_COUNT));
    for (int i=0; i < results.length(); i++) {
      final JSONObject notification=results.optJSONObject(i);
      final String dataId=notification.optString(Notification.NOTIFICATION_DATA_ID);
      final int dataType=notification.optInt(Notification.NOTIFICATION_DATA_TYPE);
      String desTemplate="";
switch (dataType) {
case Notification.DATA_TYPE_C_POINT_ARTICLE_THANK:
        desTemplate=langPropsService.get("notificationArticleThankLabel");
      final JSONObject reward12=rewardRepository.get(dataId);
    final String senderId12=reward12.optString(Reward.SENDER_ID);
  final JSONObject user12=userRepository.get(senderId12);
final String articleId12=reward12.optString(Reward.DATA_ID);
final JSONObject article12=articleRepository.get(articleId12);
if (null == article12) {
desTemplate=langPropsService.get("removedLabel");
break;
}
final String userLink12=UserExt.getUserLink(user12);
desTemplate=desTemplate.replace("{user}",userLink12);
final String articleLink12="<a href=\"" + Latkes.getServePath() + article12.optString(Article.ARTICLE_PERMALINK) + "\">" + article12.optString(Article.ARTICLE_TITLE) + "</a>";
desTemplate=desTemplate.replace("{article}",articleLink12);
break;
case Notification.DATA_TYPE_C_POINT_ARTICLE_REWARD:
desTemplate=langPropsService.get("notificationArticleRewardLabel");
final JSONObject reward7=rewardRepository.get(dataId);
final String senderId7=reward7.optString(Reward.SENDER_ID);
final JSONObject user7=userRepository.get(senderId7);
final String articleId7=reward7.optString(Reward.DATA_ID);
final JSONObject article7=articleRepository.get(articleId7);
if (null == article7) {
desTemplate=langPropsService.get("removedLabel");
break;
}
final String userLink7=UserExt.getUserLink(user7);
desTemplate=desTemplate.replace("{user}",userLink7);
final String articleLink7="<a href=\"" + Latkes.getServePath() + article7.optString(Article.ARTICLE_PERMALINK) + "\">" + article7.optString(Article.ARTICLE_TITLE) + "</a>";
desTemplate=desTemplate.replace("{article}",articleLink7);
break;
case Notification.DATA_TYPE_C_POINT_CHARGE:
desTemplate=langPropsService.get("notificationPointChargeLabel");
final JSONObject transfer5=pointtransferRepository.get(dataId);
final int sum5=transfer5.optInt(Pointtransfer.SUM);
final String memo5=transfer5.optString(Pointtransfer.DATA_ID);
final String yuan=memo5.split("-")[0];
desTemplate=desTemplate.replace("{yuan}",yuan);
desTemplate=desTemplate.replace("{point}",String.valueOf(sum5));
break;
case Notification.DATA_TYPE_C_POINT_EXCHANGE:
desTemplate=langPropsService.get("notificationPointExchangeLabel");
final JSONObject transfer6=pointtransferRepository.get(dataId);
final int sum6=transfer6.optInt(Pointtransfer.SUM);
final String yuan6=transfer6.optString(Pointtransfer.DATA_ID);
desTemplate=desTemplate.replace("{yuan}",yuan6);
desTemplate=desTemplate.replace("{point}",String.valueOf(sum6));
break;
case Notification.DATA_TYPE_C_ABUSE_POINT_DEDUCT:
desTemplate=langPropsService.get("notificationAbusePointDeductLabel");
final JSONObject transfer7=pointtransferRepository.get(dataId);
final int sum7=transfer7.optInt(Pointtransfer.SUM);
final String memo7=transfer7.optString(Pointtransfer.DATA_ID);
desTemplate=desTemplate.replace("{action}",memo7);
desTemplate=desTemplate.replace("{point}",String.valueOf(sum7));
break;
case Notification.DATA_TYPE_C_POINT_COMMENT_THANK:
desTemplate=langPropsService.get("notificationCmtThankLabel");
final JSONObject reward8=rewardRepository.get(dataId);
final String senderId8=reward8.optString(Reward.SENDER_ID);
final JSONObject user8=userRepository.get(senderId8);
final JSONObject comment8=commentRepository.get(reward8.optString(Reward.DATA_ID));
final String articleId8=comment8.optString(Comment.COMMENT_ON_ARTICLE_ID);
final JSONObject article8=articleRepository.get(articleId8);
if (null == article8) {
desTemplate=langPropsService.get("removedLabel");
break;
}
final String userLink8=UserExt.getUserLink(user8);
desTemplate=desTemplate.replace("{user}",userLink8);
final String articleLink8="<a href=\"" + Latkes.getServePath() + article8.optString(Article.ARTICLE_PERMALINK) + "\">" + article8.optString(Article.ARTICLE_TITLE) + "</a>";
desTemplate=desTemplate.replace("{article}",articleLink8);
break;
case Notification.DATA_TYPE_C_POINT_COMMENT_ACCEPT:
desTemplate=langPropsService.get("notificationCmtAcceptLabel");
final JSONObject reward33=rewardRepository.get(dataId);
final String articleId33=reward33.optString(Reward.DATA_ID);
final JSONObject article33=articleRepository.get(articleId33);
if (null == article33) {
desTemplate=langPropsService.get("removedLabel");
break;
}
final String articleAuthorId=article33.optString(Article.ARTICLE_AUTHOR_ID);
final JSONObject user33=userRepository.get(articleAuthorId);
final String userLink33=UserExt.getUserLink(user33);
desTemplate=desTemplate.replace("{user}",userLink33);
final String articleLink33="<a href=\"" + Latkes.getServePath() + article33.optString(Article.ARTICLE_PERMALINK) + "\">" + article33.optString(Article.ARTICLE_TITLE) + "</a>";
desTemplate=desTemplate.replace("{article}",Emotions.convert(articleLink33));
break;
case Notification.DATA_TYPE_C_POINT_TRANSFER:
desTemplate=langPropsService.get("notificationPointTransferLabel");
final JSONObject transfer101=pointtransferRepository.get(dataId);
final String fromId101=transfer101.optString(Pointtransfer.FROM_ID);
final JSONObject user101=userRepository.get(fromId101);
final int sum101=transfer101.optInt(Pointtransfer.SUM);
final String userLink101=UserExt.getUserLink(user101);
desTemplate=desTemplate.replace("{user}",userLink101);
desTemplate=desTemplate.replace("{amount}",String.valueOf(sum101));
break;
case Notification.DATA_TYPE_C_INVITECODE_USED:
desTemplate=langPropsService.get("notificationInvitecodeUsedLabel");
final JSONObject invitedUser=userRepository.get(dataId);
final String invitedUserLink=UserExt.getUserLink(invitedUser);
desTemplate=desTemplate.replace("{user}",invitedUserLink);
break;
case Notification.DATA_TYPE_C_INVITATION_LINK_USED:
desTemplate=langPropsService.get("notificationInvitationLinkUsedLabel");
final JSONObject invitedUser18=userRepository.get(dataId);
final String invitedUserLink18=UserExt.getUserLink(invitedUser18);
desTemplate=desTemplate.replace("{user}",invitedUserLink18);
break;
case Notification.DATA_TYPE_C_POINT_PERFECT_ARTICLE:
desTemplate=langPropsService.get("notificationPointPerfectArticleLabel");
final JSONObject article22=articleRepository.get(dataId);
if (null == article22) {
desTemplate=langPropsService.get("removedLabel");
break;
}
final String articleLink22="<a href=\"" + Latkes.getServePath() + article22.optString(Article.ARTICLE_PERMALINK) + "\">" + article22.optString(Article.ARTICLE_TITLE) + "</a>";
desTemplate=desTemplate.replace("{article}",articleLink22);
break;
case Notification.DATA_TYPE_C_POINT_REPORT_HANDLED:
desTemplate=langPropsService.get("notification36Label");
break;
default :
throw new AssertionError();
}
notification.put(Common.DESCRIPTION,desTemplate);
notification.put(Common.CREATE_TIME,new Date(notification.optLong(Keys.OBJECT_ID)));
rslts.add(notification);
}
return ret;
}
 catch (final RepositoryException e) {
LOGGER.log(Level.ERROR,"Gets [point] notifications failed",e);
return null;
}
}
