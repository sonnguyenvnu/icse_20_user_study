@OnClick(R.id.simulate_friend_backing_button) public void simulateFriendBackingButtonClick(){
  final GCM gcm=GCM.builder().title("Check it out").alert("Christopher Wright backed SKULL GRAPHIC TEE.").build();
  final Activity activity=Activity.builder().category(com.kickstarter.models.Activity.CATEGORY_BACKING).id(1).projectId(PROJECT_ID).projectPhoto(PROJECT_PHOTO).build();
  final PushNotificationEnvelope envelope=PushNotificationEnvelope.builder().activity(activity).gcm(gcm).build();
  this.pushNotifications.add(envelope);
}
