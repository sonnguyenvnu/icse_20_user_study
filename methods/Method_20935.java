public static @NonNull PushNotificationEnvelope envelope(){
  final GCM gcm=GCM.builder().alert("You've received a new push notification").title("Hello").build();
  return PushNotificationEnvelope.builder().gcm(gcm).build();
}
