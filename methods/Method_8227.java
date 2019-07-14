private void showVoiceHint(boolean hide,boolean video){
  if (getParentActivity() == null || fragmentView == null || hide && voiceHintTextView == null) {
    return;
  }
  if (voiceHintTextView == null) {
    SizeNotifierFrameLayout frameLayout=(SizeNotifierFrameLayout)fragmentView;
    int index=frameLayout.indexOfChild(chatActivityEnterView);
    if (index == -1) {
      return;
    }
    voiceHintTextView=new TextView(getParentActivity());
    voiceHintTextView.setBackgroundDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(3),Theme.getColor(Theme.key_chat_gifSaveHintBackground)));
    voiceHintTextView.setTextColor(Theme.getColor(Theme.key_chat_gifSaveHintText));
    voiceHintTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
    voiceHintTextView.setPadding(AndroidUtilities.dp(8),AndroidUtilities.dp(7),AndroidUtilities.dp(8),AndroidUtilities.dp(7));
    voiceHintTextView.setGravity(Gravity.CENTER_VERTICAL);
    voiceHintTextView.setAlpha(0.0f);
    frameLayout.addView(voiceHintTextView,index + 1,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT,Gravity.RIGHT | Gravity.BOTTOM,5,0,5,3));
  }
  if (hide) {
    if (voiceHintAnimation != null) {
      voiceHintAnimation.cancel();
      voiceHintAnimation=null;
    }
    AndroidUtilities.cancelRunOnUIThread(voiceHintHideRunnable);
    voiceHintHideRunnable=null;
    if (voiceHintTextView.getVisibility() == View.VISIBLE) {
      hideVoiceHint();
    }
    return;
  }
  voiceHintTextView.setText(video ? LocaleController.getString("HoldToVideo",R.string.HoldToVideo) : LocaleController.getString("HoldToAudio",R.string.HoldToAudio));
  if (voiceHintHideRunnable != null) {
    if (voiceHintAnimation != null) {
      voiceHintAnimation.cancel();
      voiceHintAnimation=null;
    }
 else {
      AndroidUtilities.cancelRunOnUIThread(voiceHintHideRunnable);
      AndroidUtilities.runOnUIThread(voiceHintHideRunnable=this::hideVoiceHint,2000);
      return;
    }
  }
 else   if (voiceHintAnimation != null) {
    return;
  }
  voiceHintTextView.setVisibility(View.VISIBLE);
  voiceHintAnimation=new AnimatorSet();
  voiceHintAnimation.playTogether(ObjectAnimator.ofFloat(voiceHintTextView,View.ALPHA,1.0f));
  voiceHintAnimation.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      if (animation.equals(voiceHintAnimation)) {
        voiceHintAnimation=null;
        AndroidUtilities.runOnUIThread(voiceHintHideRunnable=() -> hideVoiceHint(),2000);
      }
    }
    @Override public void onAnimationCancel(    Animator animation){
      if (animation.equals(voiceHintAnimation)) {
        voiceHintAnimation=null;
      }
    }
  }
);
  voiceHintAnimation.setDuration(300);
  voiceHintAnimation.start();
}
