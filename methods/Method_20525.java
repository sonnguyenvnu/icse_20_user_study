@OnClick(R.id.simulate_project_failure_button) public void simulateProjectFailureButtonClick(){
  final GCM gcm=GCM.builder().title("Kickstarter").alert("SKULL GRAPHIC TEE was not successfully funded.").build();
  final Activity activity=Activity.builder().category(com.kickstarter.models.Activity.CATEGORY_FAILURE).id(4).projectId(PROJECT_ID).projectPhoto(PROJECT_PHOTO).build();
  final PushNotificationEnvelope envelope=PushNotificationEnvelope.builder().activity(activity).gcm(gcm).build();
  this.pushNotifications.add(envelope);
}
