private void setCurrentCaption(MessageObject messageObject,final CharSequence caption,boolean animated){
  if (needCaptionLayout) {
    if (captionTextView.getParent() != pickerView) {
      captionTextView.setBackgroundDrawable(null);
      containerView.removeView(captionTextView);
      pickerView.addView(captionTextView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,Gravity.BOTTOM | Gravity.LEFT,0,0,76,48));
    }
  }
 else {
    if (captionTextView.getParent() != containerView) {
      captionTextView.setBackgroundColor(0x7f000000);
      pickerView.removeView(captionTextView);
      containerView.addView(captionTextView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,Gravity.BOTTOM | Gravity.LEFT,0,0,0,48));
    }
  }
  if (isCurrentVideo) {
    captionTextView.setMaxLines(1);
    captionTextView.setSingleLine(true);
  }
 else {
    captionTextView.setSingleLine(false);
    captionTextView.setMaxLines(10);
  }
  boolean wasVisisble=captionTextView.getTag() != null;
  if (!TextUtils.isEmpty(caption)) {
    Theme.createChatResources(null,true);
    CharSequence str;
    if (messageObject != null && !messageObject.messageOwner.entities.isEmpty()) {
      Spannable spannableString=SpannableString.valueOf(caption.toString());
      messageObject.addEntitiesToText(spannableString,true,false);
      str=Emoji.replaceEmoji(spannableString,captionTextView.getPaint().getFontMetricsInt(),AndroidUtilities.dp(20),false);
    }
 else {
      str=Emoji.replaceEmoji(new SpannableStringBuilder(caption),captionTextView.getPaint().getFontMetricsInt(),AndroidUtilities.dp(20),false);
    }
    captionTextView.setTag(str);
    if (currentCaptionAnimation != null) {
      currentCaptionAnimation.cancel();
      currentCaptionAnimation=null;
    }
    try {
      captionTextView.setText(str);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    captionTextView.setScrollY(0);
    captionTextView.setTextColor(0xffffffff);
    boolean visible=isActionBarVisible && (bottomLayout.getVisibility() == View.VISIBLE || pickerView.getVisibility() == View.VISIBLE);
    if (visible) {
      captionTextView.setVisibility(View.VISIBLE);
      if (animated && !wasVisisble) {
        currentCaptionAnimation=new AnimatorSet();
        currentCaptionAnimation.setDuration(200);
        currentCaptionAnimation.setInterpolator(decelerateInterpolator);
        currentCaptionAnimation.addListener(new AnimatorListenerAdapter(){
          @Override public void onAnimationEnd(          Animator animation){
            if (animation.equals(currentCaptionAnimation)) {
              currentCaptionAnimation=null;
            }
          }
        }
);
        currentCaptionAnimation.playTogether(ObjectAnimator.ofFloat(captionTextView,View.ALPHA,0.0f,1.0f),ObjectAnimator.ofFloat(captionTextView,View.TRANSLATION_Y,AndroidUtilities.dp(5),0.0f));
        currentCaptionAnimation.start();
      }
 else {
        captionTextView.setAlpha(1.0f);
      }
    }
 else     if (captionTextView.getVisibility() == View.VISIBLE) {
      captionTextView.setVisibility(View.INVISIBLE);
      captionTextView.setAlpha(0.0f);
    }
  }
 else {
    if (needCaptionLayout) {
      captionTextView.setText(LocaleController.getString("AddCaption",R.string.AddCaption));
      captionTextView.setTag("empty");
      captionTextView.setVisibility(View.VISIBLE);
      captionTextView.setTextColor(0xb2ffffff);
    }
 else {
      captionTextView.setTextColor(0xffffffff);
      captionTextView.setTag(null);
      if (animated && wasVisisble) {
        currentCaptionAnimation=new AnimatorSet();
        currentCaptionAnimation.setDuration(200);
        currentCaptionAnimation.setInterpolator(decelerateInterpolator);
        currentCaptionAnimation.addListener(new AnimatorListenerAdapter(){
          @Override public void onAnimationEnd(          Animator animation){
            if (animation.equals(currentCaptionAnimation)) {
              captionTextView.setVisibility(View.INVISIBLE);
              currentCaptionAnimation=null;
            }
          }
          @Override public void onAnimationCancel(          Animator animation){
            if (animation.equals(currentCaptionAnimation)) {
              currentCaptionAnimation=null;
            }
          }
        }
);
        currentCaptionAnimation.playTogether(ObjectAnimator.ofFloat(captionTextView,View.ALPHA,0.0f),ObjectAnimator.ofFloat(captionTextView,View.TRANSLATION_Y,AndroidUtilities.dp(5)));
        currentCaptionAnimation.start();
      }
 else {
        captionTextView.setVisibility(View.INVISIBLE);
      }
    }
  }
}
