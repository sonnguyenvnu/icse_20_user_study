@Override public void onSubscribeOrMute(boolean mute){
  if (getIssue() == null)   return;
  makeRestCall(mute ? RestProvider.getNotificationService(isEnterprise()).subscribe(getIssue().getId(),new NotificationSubscriptionBodyModel(false,true)) : RestProvider.getNotificationService(isEnterprise()).subscribe(getIssue().getId(),new NotificationSubscriptionBodyModel(true,false)),booleanResponse -> {
    if (booleanResponse.code() == 204 || booleanResponse.code() == 200) {
      sendToView(view -> view.showMessage(R.string.success,R.string.successfully_submitted));
    }
 else {
      sendToView(view -> view.showMessage(R.string.error,R.string.network_error));
    }
  }
);
}
