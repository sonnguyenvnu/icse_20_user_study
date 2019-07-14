@OnClick(R.id.simulate_project_cancellation_button) public void simulateProjectCancellationButtonClick(){
  final GCM gcm=GCM.builder().title("Kickstarter").alert("SKULL GRAPHIC TEE has been canceled.").build();
  final Activity activity=Activity.builder().category(com.kickstarter.models.Activity.CATEGORY_CANCELLATION).id(3).projectId(PROJECT_ID).projectPhoto(PROJECT_PHOTO).build();
  final PushNotificationEnvelope envelope=PushNotificationEnvelope.builder().activity(activity).gcm(gcm).build();
  this.pushNotifications.add(envelope);
}
