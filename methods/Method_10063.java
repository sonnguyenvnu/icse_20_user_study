/** 
 * Gets the count of unread 'point' notifications of a user specified with the given user id.
 * @param userId the given user id
 * @return count of unread notifications, returns {@code 0} if occurs exception
 */
public int getUnreadPointNotificationCount(final String userId){
  final List<Filter> filters=new ArrayList<>();
  filters.add(new PropertyFilter(Notification.NOTIFICATION_USER_ID,FilterOperator.EQUAL,userId));
  filters.add(new PropertyFilter(Notification.NOTIFICATION_HAS_READ,FilterOperator.EQUAL,false));
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
  final Query query=new Query().setFilter(new CompositeFilter(CompositeFilterOperator.AND,filters));
  try {
    final JSONObject result=notificationRepository.get(query);
    return result.optJSONObject(Pagination.PAGINATION).optInt(Pagination.PAGINATION_RECORD_COUNT);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets [point] notification count failed [userId=" + userId + "]",e);
    return 0;
  }
}
