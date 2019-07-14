/** 
 * Removes the specified user's notifications of the specified type.
 * @param userId the specified user id
 * @param type   the specified notification type
 */
@Transactional public void removeNotifications(final String userId,final int type){
  final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Notification.NOTIFICATION_USER_ID,FilterOperator.EQUAL,userId),new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,type)));
  try {
    notificationRepository.remove(query);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Removes user [id=" + userId + "]'s notifications [type=" + type + "] failed",e);
  }
}
