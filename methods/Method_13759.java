/** 
 * {@inheritDoc}
 */
@Override public void markNotified(NotificationType type,Recipient recipient){
  recipient.getScheduledNotifications().get(type).setLastNotified(new Date());
  repository.save(recipient);
}
