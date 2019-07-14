private void needLayout(){
  FrameLayout.LayoutParams layoutParams;
  int newTop=(actionBar.getOccupyStatusBar() ? AndroidUtilities.statusBarHeight : 0) + ActionBar.getCurrentActionBarHeight();
  if (listView != null) {
    layoutParams=(FrameLayout.LayoutParams)listView.getLayoutParams();
    if (layoutParams.topMargin != newTop) {
      layoutParams.topMargin=newTop;
      listView.setLayoutParams(layoutParams);
      extraHeightView.setTranslationY(newTop);
    }
    layoutParams=(FrameLayout.LayoutParams)emptyView.getLayoutParams();
    if (layoutParams.topMargin != newTop) {
      layoutParams.topMargin=newTop;
      emptyView.setLayoutParams(layoutParams);
    }
  }
  if (avatarContainer != null) {
    int currentExtraHeight;
    if (avatarContainer.getVisibility() == View.VISIBLE) {
      currentExtraHeight=extraHeight;
    }
 else {
      currentExtraHeight=0;
    }
    float diff=currentExtraHeight / (float)AndroidUtilities.dp(88);
    extraHeightView.setScaleY(diff);
    shadowView.setTranslationY(newTop + currentExtraHeight);
    writeButton.setTranslationY((actionBar.getOccupyStatusBar() ? AndroidUtilities.statusBarHeight : 0) + ActionBar.getCurrentActionBarHeight() + currentExtraHeight - AndroidUtilities.dp(29.5f));
    final boolean setVisible=diff > 0.2f;
    boolean currentVisible=writeButton.getTag() == null;
    if (setVisible != currentVisible) {
      if (setVisible) {
        writeButton.setTag(null);
        writeButton.setVisibility(View.VISIBLE);
      }
 else {
        writeButton.setTag(0);
      }
      if (writeButtonAnimation != null) {
        AnimatorSet old=writeButtonAnimation;
        writeButtonAnimation=null;
        old.cancel();
      }
      writeButtonAnimation=new AnimatorSet();
      if (setVisible) {
        writeButtonAnimation.setInterpolator(new DecelerateInterpolator());
        writeButtonAnimation.playTogether(ObjectAnimator.ofFloat(writeButton,"scaleX",1.0f),ObjectAnimator.ofFloat(writeButton,"scaleY",1.0f),ObjectAnimator.ofFloat(writeButton,"alpha",1.0f));
      }
 else {
        writeButtonAnimation.setInterpolator(new AccelerateInterpolator());
        writeButtonAnimation.playTogether(ObjectAnimator.ofFloat(writeButton,"scaleX",0.2f),ObjectAnimator.ofFloat(writeButton,"scaleY",0.2f),ObjectAnimator.ofFloat(writeButton,"alpha",0.0f));
      }
      writeButtonAnimation.setDuration(150);
      writeButtonAnimation.addListener(new AnimatorListenerAdapter(){
        @Override public void onAnimationEnd(        Animator animation){
          if (writeButtonAnimation != null && writeButtonAnimation.equals(animation)) {
            writeButton.setVisibility(setVisible ? View.VISIBLE : View.GONE);
            writeButtonAnimation=null;
          }
        }
      }
);
      writeButtonAnimation.start();
    }
    avatarContainer.setScaleX((42 + 18 * diff) / 42.0f);
    avatarContainer.setScaleY((42 + 18 * diff) / 42.0f);
    avatarProgressView.setSize(AndroidUtilities.dp(26 / avatarContainer.getScaleX()));
    avatarProgressView.setStrokeWidth(3 / avatarContainer.getScaleX());
    float avatarY=(actionBar.getOccupyStatusBar() ? AndroidUtilities.statusBarHeight : 0) + ActionBar.getCurrentActionBarHeight() / 2.0f * (1.0f + diff) - 21 * AndroidUtilities.density + 27 * AndroidUtilities.density * diff;
    avatarContainer.setTranslationY((float)Math.ceil(avatarY));
    nameTextView.setTranslationY((float)Math.floor(avatarY) - (float)Math.ceil(AndroidUtilities.density) + (float)Math.floor(7 * AndroidUtilities.density * diff));
    onlineTextView.setTranslationY((float)Math.floor(avatarY) + AndroidUtilities.dp(22) + (float)Math.floor(11 * AndroidUtilities.density) * diff);
    nameTextView.setScaleX(1.0f + 0.12f * diff);
    nameTextView.setScaleY(1.0f + 0.12f * diff);
    if (LocaleController.isRTL) {
      avatarContainer.setTranslationX(AndroidUtilities.dp(47 + 48) * diff);
      nameTextView.setTranslationX((21 + 48) * AndroidUtilities.density * diff);
      onlineTextView.setTranslationX((21 + 48) * AndroidUtilities.density * diff);
    }
 else {
      avatarContainer.setTranslationX(-AndroidUtilities.dp(47) * diff);
      nameTextView.setTranslationX(-21 * AndroidUtilities.density * diff);
      onlineTextView.setTranslationX(-21 * AndroidUtilities.density * diff);
    }
  }
}
