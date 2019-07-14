private void showMediaBannedHint(){
  if (getParentActivity() == null || currentChat == null || fragmentView == null || mediaBanTooltip != null && mediaBanTooltip.getVisibility() == View.VISIBLE) {
    return;
  }
  SizeNotifierFrameLayout frameLayout=(SizeNotifierFrameLayout)fragmentView;
  int index=frameLayout.indexOfChild(chatActivityEnterView);
  if (index == -1) {
    return;
  }
  if (mediaBanTooltip == null) {
    mediaBanTooltip=new CorrectlyMeasuringTextView(getParentActivity());
    mediaBanTooltip.setBackgroundDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(3),Theme.getColor(Theme.key_chat_gifSaveHintBackground)));
    mediaBanTooltip.setTextColor(Theme.getColor(Theme.key_chat_gifSaveHintText));
    mediaBanTooltip.setPadding(AndroidUtilities.dp(8),AndroidUtilities.dp(7),AndroidUtilities.dp(8),AndroidUtilities.dp(7));
    mediaBanTooltip.setGravity(Gravity.CENTER_VERTICAL);
    mediaBanTooltip.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
    mediaBanTooltip.setVisibility(View.GONE);
    frameLayout.addView(mediaBanTooltip,index + 1,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT,Gravity.RIGHT | Gravity.BOTTOM,30,0,5,3));
  }
  if (ChatObject.isActionBannedByDefault(currentChat,ChatObject.ACTION_SEND_MEDIA)) {
    mediaBanTooltip.setText(LocaleController.getString("GlobalAttachMediaRestricted",R.string.GlobalAttachMediaRestricted));
  }
 else {
    if (currentChat.banned_rights == null) {
      return;
    }
    if (AndroidUtilities.isBannedForever(currentChat.banned_rights)) {
      mediaBanTooltip.setText(LocaleController.getString("AttachMediaRestrictedForever",R.string.AttachMediaRestrictedForever));
    }
 else {
      mediaBanTooltip.setText(LocaleController.formatString("AttachMediaRestricted",R.string.AttachMediaRestricted,LocaleController.formatDateForBan(currentChat.banned_rights.until_date)));
    }
  }
  mediaBanTooltip.setVisibility(View.VISIBLE);
  AnimatorSet AnimatorSet=new AnimatorSet();
  AnimatorSet.playTogether(ObjectAnimator.ofFloat(mediaBanTooltip,View.ALPHA,0.0f,1.0f));
  AnimatorSet.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      AndroidUtilities.runOnUIThread(() -> {
        if (mediaBanTooltip == null) {
          return;
        }
        AnimatorSet AnimatorSet=new AnimatorSet();
        AnimatorSet.playTogether(ObjectAnimator.ofFloat(mediaBanTooltip,View.ALPHA,0.0f));
        AnimatorSet.addListener(new AnimatorListenerAdapter(){
          @Override public void onAnimationEnd(          Animator animation){
            if (mediaBanTooltip != null) {
              mediaBanTooltip.setVisibility(View.GONE);
            }
          }
        }
);
        AnimatorSet.setDuration(300);
        AnimatorSet.start();
      }
,5000);
    }
  }
);
  AnimatorSet.setDuration(300);
  AnimatorSet.start();
}
