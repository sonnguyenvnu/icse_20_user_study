public void trackPushNotification(final @NonNull PushNotificationEnvelope envelope){
  final Map<String,Object> properties=new HashMap<String,Object>(){
{
      put("notification_type","push");
      if (envelope.activity() != null) {
        put("notification_subject","activity");
        put("notification_activity_category",envelope.activity().category());
      }
    }
  }
;
  this.client.track(KoalaEvent.NOTIFICATION_OPENED_LEGACY,properties);
  this.client.track(KoalaEvent.OPENED_NOTIFICATION,properties);
}
