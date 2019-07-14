/** 
 * Gets the count of unread 'sys announce' notifications of a user specified with the given user id.
 * @param userId the given user id
 * @return count of unread notifications, returns {@code 0} if occurs exception
 */
public int getUnreadSysAnnounceNotificationCount(final String userId){
  final List<Filter> filters=new ArrayList<>();
  filters.add(new PropertyFilter(Notification.NOTIFICATION_USER_ID,FilterOperator.EQUAL,userId));
  filters.add(new PropertyFilter(Notification.NOTIFICATION_HAS_READ,FilterOperator.EQUAL,false));
  final List<Filter> subFilters=new ArrayList<>();
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_SYS_ANNOUNCE_ARTICLE));
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_SYS_ANNOUNCE_NEW_USER));
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_SYS_ANNOUNCE_ROLE_CHANGED));
  filters.add(new CompositeFilter(CompositeFilterOperator.OR,subFilters));
  final Query query=new Query().setFilter(new CompositeFilter(CompositeFilterOperator.AND,filters));
  try {
    final JSONObject result=notificationRepository.get(query);
    return result.optJSONObject(Pagination.PAGINATION).optInt(Pagination.PAGINATION_RECORD_COUNT);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets [sys_announce] notification count failed [userId=" + userId + "]",e);
    return 0;
  }
}
