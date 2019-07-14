private void updateHint(){
  if (!isAlwaysShare && !isNeverShare && !addToGroup) {
    if (chatType == ChatObject.CHAT_TYPE_CHANNEL) {
      actionBar.setSubtitle(LocaleController.formatPluralString("Members",selectedContacts.size()));
    }
 else {
      if (selectedContacts.size() == 0) {
        actionBar.setSubtitle(LocaleController.formatString("MembersCountZero",R.string.MembersCountZero,LocaleController.formatPluralString("Members",maxCount)));
      }
 else {
        actionBar.setSubtitle(LocaleController.formatString("MembersCount",R.string.MembersCount,selectedContacts.size(),maxCount));
      }
    }
  }
  if (chatType != ChatObject.CHAT_TYPE_CHANNEL) {
    if (doneButtonVisible && allSpans.isEmpty()) {
      if (currentDoneButtonAnimation != null) {
        currentDoneButtonAnimation.cancel();
      }
      currentDoneButtonAnimation=new AnimatorSet();
      currentDoneButtonAnimation.playTogether(ObjectAnimator.ofFloat(floatingButton,"scaleX",0.0f),ObjectAnimator.ofFloat(floatingButton,"scaleY",0.0f),ObjectAnimator.ofFloat(floatingButton,"alpha",0.0f));
      currentDoneButtonAnimation.addListener(new AnimatorListenerAdapter(){
        @Override public void onAnimationEnd(        Animator animation){
          floatingButton.setVisibility(View.INVISIBLE);
        }
      }
);
      currentDoneButtonAnimation.setDuration(180);
      currentDoneButtonAnimation.start();
      doneButtonVisible=false;
    }
 else     if (!doneButtonVisible && !allSpans.isEmpty()) {
      if (currentDoneButtonAnimation != null) {
        currentDoneButtonAnimation.cancel();
      }
      currentDoneButtonAnimation=new AnimatorSet();
      floatingButton.setVisibility(View.VISIBLE);
      currentDoneButtonAnimation.playTogether(ObjectAnimator.ofFloat(floatingButton,"scaleX",1.0f),ObjectAnimator.ofFloat(floatingButton,"scaleY",1.0f),ObjectAnimator.ofFloat(floatingButton,"alpha",1.0f));
      currentDoneButtonAnimation.setDuration(180);
      currentDoneButtonAnimation.start();
      doneButtonVisible=true;
    }
  }
}
