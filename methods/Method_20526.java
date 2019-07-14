@OnClick(R.id.simulate_project_launch_button) public void simulateProjectLaunchButtonClick(){
  final GCM gcm=GCM.builder().title("Want to be the first backer?").alert("Taylor Moore just launched a project!").build();
  final Activity activity=Activity.builder().category(com.kickstarter.models.Activity.CATEGORY_LAUNCH).id(5).projectId(PROJECT_ID).build();
  final PushNotificationEnvelope envelope=PushNotificationEnvelope.builder().activity(activity).gcm(gcm).build();
  this.pushNotifications.add(envelope);
}
