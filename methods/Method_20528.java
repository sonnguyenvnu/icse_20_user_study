@OnClick(R.id.simulate_project_update_button) public void simulateProjectUpdateButtonClick(){
  final GCM gcm=GCM.builder().title("News from Taylor Moore").alert("Update #1 posted by SKULL GRAPHIC TEE.").build();
  final Activity activity=Activity.builder().category(com.kickstarter.models.Activity.CATEGORY_UPDATE).id(7).projectId(PROJECT_ID).projectPhoto(PROJECT_PHOTO).updateId(1033848L).build();
  final PushNotificationEnvelope envelope=PushNotificationEnvelope.builder().activity(activity).gcm(gcm).build();
  this.pushNotifications.add(envelope);
}
