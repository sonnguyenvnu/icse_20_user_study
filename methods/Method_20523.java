@OnClick(R.id.simulate_friend_follow_button) public void simulateFriendFollowButtonClick(){
  final GCM gcm=GCM.builder().title("You're in good company").alert("Christopher Wright is following you on Kickstarter!").build();
  final Activity activity=Activity.builder().category(com.kickstarter.models.Activity.CATEGORY_FOLLOW).id(2).userPhoto(USER_PHOTO).build();
  final PushNotificationEnvelope envelope=PushNotificationEnvelope.builder().activity(activity).gcm(gcm).build();
  this.pushNotifications.add(envelope);
}
