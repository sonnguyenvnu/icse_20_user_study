private @NonNull PushNotificationEnvelope projectSuccessEnvelope(){
  final GCM gcm=GCM.builder().title("Time to celebrate!").alert("SKULL GRAPHIC TEE has been successfully funded.").build();
  final Activity activity=Activity.builder().category(com.kickstarter.models.Activity.CATEGORY_SUCCESS).id(6).projectId(PROJECT_ID).projectPhoto(PROJECT_PHOTO).build();
  return PushNotificationEnvelope.builder().activity(activity).gcm(gcm).build();
}
