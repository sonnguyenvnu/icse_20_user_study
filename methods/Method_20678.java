public void add(final @NonNull PushNotificationEnvelope envelope){
  this.notifications.onNext(envelope);
}
