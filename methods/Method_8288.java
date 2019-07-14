private void updateSpamView(){
  if (reportSpamView == null) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("no spam view found");
    }
    return;
  }
  SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
  boolean show;
  if (currentEncryptedChat != null) {
    show=!(currentEncryptedChat.admin_id == UserConfig.getInstance(currentAccount).getClientUserId() || ContactsController.getInstance(currentAccount).isLoadingContacts()) && ContactsController.getInstance(currentAccount).contactsDict.get(currentUser.id) == null;
    if (show && preferences.getInt("spam3_" + dialog_id,0) == 1) {
      show=false;
    }
  }
 else {
    show=preferences.getInt("spam3_" + dialog_id,0) == 2;
  }
  if (!show) {
    if (reportSpamView.getTag() == null) {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d("hide spam button");
      }
      reportSpamView.setTag(1);
      if (reportSpamViewAnimator != null) {
        reportSpamViewAnimator.cancel();
      }
      reportSpamViewAnimator=new AnimatorSet();
      reportSpamViewAnimator.playTogether(ObjectAnimator.ofFloat(reportSpamView,View.TRANSLATION_Y,-AndroidUtilities.dp(50)));
      reportSpamViewAnimator.setDuration(200);
      reportSpamViewAnimator.addListener(new AnimatorListenerAdapter(){
        @Override public void onAnimationEnd(        Animator animation){
          if (reportSpamViewAnimator != null && reportSpamViewAnimator.equals(animation)) {
            reportSpamView.setVisibility(View.GONE);
            reportSpamViewAnimator=null;
          }
        }
        @Override public void onAnimationCancel(        Animator animation){
          if (reportSpamViewAnimator != null && reportSpamViewAnimator.equals(animation)) {
            reportSpamViewAnimator=null;
          }
        }
      }
);
      reportSpamViewAnimator.start();
    }
  }
 else {
    if (reportSpamView.getTag() != null) {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d("show spam button");
      }
      reportSpamView.setTag(null);
      reportSpamView.setVisibility(View.VISIBLE);
      if (reportSpamViewAnimator != null) {
        reportSpamViewAnimator.cancel();
      }
      reportSpamViewAnimator=new AnimatorSet();
      reportSpamViewAnimator.playTogether(ObjectAnimator.ofFloat(reportSpamView,View.TRANSLATION_Y,0));
      reportSpamViewAnimator.setDuration(200);
      reportSpamViewAnimator.addListener(new AnimatorListenerAdapter(){
        @Override public void onAnimationEnd(        Animator animation){
          if (reportSpamViewAnimator != null && reportSpamViewAnimator.equals(animation)) {
            reportSpamViewAnimator=null;
          }
        }
        @Override public void onAnimationCancel(        Animator animation){
          if (reportSpamViewAnimator != null && reportSpamViewAnimator.equals(animation)) {
            reportSpamViewAnimator=null;
          }
        }
      }
);
      reportSpamViewAnimator.start();
    }
  }
  checkListViewPaddings();
}
