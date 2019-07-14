/** 
 * Makes the specified user's all notifications as read.
 * @param userId the specified user id
 */
public void makeAllRead(final String userId){
  final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Notification.NOTIFICATION_USER_ID,FilterOperator.EQUAL,userId),new PropertyFilter(Notification.NOTIFICATION_HAS_READ,FilterOperator.EQUAL,false)));
  try {
    final Set<JSONObject> notifications=CollectionUtils.jsonArrayToSet(notificationRepository.get(query).optJSONArray(Keys.RESULTS));
    makeRead(notifications);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Makes read failed",e);
  }
}
