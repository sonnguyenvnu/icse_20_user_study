/** 
 * Gets 'sys announce' type notifications with the specified user id, current page number and page size.
 * @param userId         the specified user id
 * @param currentPageNum the specified page number
 * @param pageSize       the specified page size
 * @return result json object, for example,      <pre>{ "paginationRecordCount": int, "rslts": java.util.List[{ "oId": "", // notification record id "description": "", "hasRead": boolean }, ....] } </pre>
 */
public JSONObject getSysAnnounceNotifications(final String userId,final int currentPageNum,final int pageSize){
  final JSONObject ret=new JSONObject();
  final List<JSONObject> rslts=new ArrayList<>();
  ret.put(Keys.RESULTS,(Object)rslts);
  final List<Filter> filters=new ArrayList<>();
  filters.add(new PropertyFilter(Notification.NOTIFICATION_USER_ID,FilterOperator.EQUAL,userId));
  final List<Filter> subFilters=new ArrayList<>();
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_SYS_ANNOUNCE_ARTICLE));
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_SYS_ANNOUNCE_NEW_USER));
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_SYS_ANNOUNCE_ROLE_CHANGED));
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
      String desTemplate;
switch (dataType) {
case Notification.DATA_TYPE_C_SYS_ANNOUNCE_NEW_USER:
        desTemplate=langPropsService.get("notificationSysNewUser1Label");
      break;
case Notification.DATA_TYPE_C_SYS_ANNOUNCE_ARTICLE:
    desTemplate=langPropsService.get("notificationSysArticleLabel");
  final JSONObject article15=articleRepository.get(dataId);
if (null == article15) {
  desTemplate=langPropsService.get("removedLabel");
  break;
}
final String articleLink15="<a href=\"" + Latkes.getServePath() + article15.optString(Article.ARTICLE_PERMALINK) + "\">" + article15.optString(Article.ARTICLE_TITLE) + "</a>";
desTemplate=desTemplate.replace("{article}",articleLink15);
break;
case Notification.DATA_TYPE_C_SYS_ANNOUNCE_ROLE_CHANGED:
desTemplate=langPropsService.get("notificationSysRoleChangedLabel");
final String oldRoleId=dataId.split("-")[0];
final String newRoleId=dataId.split("-")[1];
final JSONObject oldRole=roleQueryService.getRole(oldRoleId);
final JSONObject newRole=roleQueryService.getRole(newRoleId);
desTemplate=desTemplate.replace("{oldRole}",oldRole.optString(Role.ROLE_NAME));
desTemplate=desTemplate.replace("{newRole}",newRole.optString(Role.ROLE_NAME));
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
LOGGER.log(Level.ERROR,"Gets [sys_announce] notifications failed",e);
return null;
}
}
