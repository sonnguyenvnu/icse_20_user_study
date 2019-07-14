private void checkPlayer(boolean create){
  MessageObject messageObject=MediaController.getInstance().getPlayingMessageObject();
  View fragmentView=fragment.getFragmentView();
  if (!create && fragmentView != null) {
    if (fragmentView.getParent() == null || ((View)fragmentView.getParent()).getVisibility() != VISIBLE) {
      create=true;
    }
  }
  if (messageObject == null || messageObject.getId() == 0 || messageObject.isVideo()) {
    lastMessageObject=null;
    boolean callAvailable=VoIPService.getSharedInstance() != null && VoIPService.getSharedInstance().getCallState() != VoIPService.STATE_WAITING_INCOMING;
    if (callAvailable) {
      checkCall(false);
      return;
    }
    if (visible) {
      visible=false;
      if (create) {
        if (getVisibility() != GONE) {
          setVisibility(GONE);
        }
        setTopPadding(0);
      }
 else {
        if (animatorSet != null) {
          animatorSet.cancel();
          animatorSet=null;
        }
        animatorSet=new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(this,"topPadding",0));
        animatorSet.setDuration(200);
        animatorSet.addListener(new AnimatorListenerAdapter(){
          @Override public void onAnimationEnd(          Animator animation){
            if (animatorSet != null && animatorSet.equals(animation)) {
              setVisibility(GONE);
              animatorSet=null;
            }
          }
        }
);
        animatorSet.start();
      }
    }
  }
 else {
    int prevStyle=currentStyle;
    updateStyle(0);
    if (create && topPadding == 0) {
      setTopPadding(AndroidUtilities.dp2(36));
      if (additionalContextView != null && additionalContextView.getVisibility() == VISIBLE) {
        ((LayoutParams)getLayoutParams()).topMargin=-AndroidUtilities.dp(72);
      }
 else {
        ((LayoutParams)getLayoutParams()).topMargin=-AndroidUtilities.dp(36);
      }
      yPosition=0;
    }
    if (!visible) {
      if (!create) {
        if (animatorSet != null) {
          animatorSet.cancel();
          animatorSet=null;
        }
        animatorSet=new AnimatorSet();
        if (additionalContextView != null && additionalContextView.getVisibility() == VISIBLE) {
          ((LayoutParams)getLayoutParams()).topMargin=-AndroidUtilities.dp(72);
        }
 else {
          ((LayoutParams)getLayoutParams()).topMargin=-AndroidUtilities.dp(36);
        }
        animatorSet.playTogether(ObjectAnimator.ofFloat(this,"topPadding",AndroidUtilities.dp2(36)));
        animatorSet.setDuration(200);
        animatorSet.addListener(new AnimatorListenerAdapter(){
          @Override public void onAnimationEnd(          Animator animation){
            if (animatorSet != null && animatorSet.equals(animation)) {
              animatorSet=null;
            }
          }
        }
);
        animatorSet.start();
      }
      visible=true;
      setVisibility(VISIBLE);
    }
    if (MediaController.getInstance().isMessagePaused()) {
      playButton.setImageResource(R.drawable.miniplayer_play);
      playButton.setContentDescription(LocaleController.getString("AccActionPlay",R.string.AccActionPlay));
    }
 else {
      playButton.setImageResource(R.drawable.miniplayer_pause);
      playButton.setContentDescription(LocaleController.getString("AccActionPause",R.string.AccActionPause));
    }
    if (lastMessageObject != messageObject || prevStyle != 0) {
      lastMessageObject=messageObject;
      SpannableStringBuilder stringBuilder;
      if (lastMessageObject.isVoice() || lastMessageObject.isRoundVideo()) {
        if (playbackSpeedButton != null) {
          playbackSpeedButton.setAlpha(1.0f);
          playbackSpeedButton.setEnabled(true);
        }
        titleTextView.setPadding(0,0,AndroidUtilities.dp(44),0);
        stringBuilder=new SpannableStringBuilder(String.format("%s %s",messageObject.getMusicAuthor(),messageObject.getMusicTitle()));
        titleTextView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
      }
 else {
        if (playbackSpeedButton != null) {
          playbackSpeedButton.setAlpha(0.0f);
          playbackSpeedButton.setEnabled(false);
        }
        titleTextView.setPadding(0,0,0,0);
        stringBuilder=new SpannableStringBuilder(String.format("%s - %s",messageObject.getMusicAuthor(),messageObject.getMusicTitle()));
        titleTextView.setEllipsize(TextUtils.TruncateAt.END);
      }
      TypefaceSpan span=new TypefaceSpan(AndroidUtilities.getTypeface("fonts/rmedium.ttf"),0,Theme.getColor(Theme.key_inappPlayerPerformer));
      stringBuilder.setSpan(span,0,messageObject.getMusicAuthor().length(),Spanned.SPAN_INCLUSIVE_INCLUSIVE);
      titleTextView.setText(stringBuilder);
    }
  }
}
