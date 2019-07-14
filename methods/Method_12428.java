private NotificationFilter createFilter(@Nullable InstanceId id,String name,@Nullable Long ttl){
  Instant expiry=ttl != null && ttl >= 0 ? Instant.now().plusMillis(ttl) : null;
  if (id != null) {
    return new InstanceIdNotificationFilter(id,expiry);
  }
 else {
    return new ApplicationNameNotificationFilter(name,expiry);
  }
}
