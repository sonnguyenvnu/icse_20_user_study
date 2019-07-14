/** 
 * Makes the specified user's notifications of the specified type as read.
 * @param userId the specified user id
 * @param type   the specified notification type
 */
public void makeRead(final String userId,final int type){
  final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Notification.NOTIFICATION_USER_ID,FilterOperator.EQUAL,userId),new PropertyFilter(Notification.NOTIFICATION_HAS_READ,FilterOperator.EQUAL,false),new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,type)));
  try {
    final Set<JSONObject> notifications=CollectionUtils.jsonArrayToSet(notificationRepository.get(query).optJSONArray(Keys.RESULTS));
    makeRead(notifications);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Makes read failed",e);
  }
}
