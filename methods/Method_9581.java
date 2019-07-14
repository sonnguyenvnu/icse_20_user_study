private void needLayout(){
  FrameLayout.LayoutParams layoutParams;
  int newTop=(actionBar.getOccupyStatusBar() ? AndroidUtilities.statusBarHeight : 0) + ActionBar.getCurrentActionBarHeight();
  if (listView != null && !openAnimationInProgress) {
    layoutParams=(FrameLayout.LayoutParams)listView.getLayoutParams();
    if (layoutParams.topMargin != newTop) {
      layoutParams.topMargin=newTop;
      listView.setLayoutParams(layoutParams);
    }
  }
  if (avatarImage != null) {
    float diff=extraHeight / (float)AndroidUtilities.dp(88);
    listView.setTopGlowOffset(extraHeight);
    if (writeButton != null) {
      writeButton.setTranslationY((actionBar.getOccupyStatusBar() ? AndroidUtilities.statusBarHeight : 0) + ActionBar.getCurrentActionBarHeight() + extraHeight - AndroidUtilities.dp(29.5f));
      if (!openAnimationInProgress) {
        final boolean setVisible=diff > 0.2f;
        boolean currentVisible=writeButton.getTag() == null;
        if (setVisible != currentVisible) {
          if (setVisible) {
            writeButton.setTag(null);
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
            writeButtonAnimation.playTogether(ObjectAnimator.ofFloat(writeButton,View.SCALE_X,1.0f),ObjectAnimator.ofFloat(writeButton,View.SCALE_Y,1.0f),ObjectAnimator.ofFloat(writeButton,View.ALPHA,1.0f));
          }
 else {
            writeButtonAnimation.setInterpolator(new AccelerateInterpolator());
            writeButtonAnimation.playTogether(ObjectAnimator.ofFloat(writeButton,View.SCALE_X,0.2f),ObjectAnimator.ofFloat(writeButton,View.SCALE_Y,0.2f),ObjectAnimator.ofFloat(writeButton,View.ALPHA,0.0f));
          }
          writeButtonAnimation.setDuration(150);
          writeButtonAnimation.addListener(new AnimatorListenerAdapter(){
            @Override public void onAnimationEnd(            Animator animation){
              if (writeButtonAnimation != null && writeButtonAnimation.equals(animation)) {
                writeButtonAnimation=null;
              }
            }
          }
);
          writeButtonAnimation.start();
        }
      }
    }
    float avatarY=(actionBar.getOccupyStatusBar() ? AndroidUtilities.statusBarHeight : 0) + ActionBar.getCurrentActionBarHeight() / 2.0f * (1.0f + diff) - 21 * AndroidUtilities.density + 27 * AndroidUtilities.density * diff;
    avatarImage.setScaleX((42 + 18 * diff) / 42.0f);
    avatarImage.setScaleY((42 + 18 * diff) / 42.0f);
    avatarImage.setTranslationX(-AndroidUtilities.dp(47) * diff);
    avatarImage.setTranslationY((float)Math.ceil(avatarY));
    for (int a=0; a < 2; a++) {
      if (nameTextView[a] == null) {
        continue;
      }
      nameTextView[a].setTranslationX(-21 * AndroidUtilities.density * diff);
      nameTextView[a].setTranslationY((float)Math.floor(avatarY) + AndroidUtilities.dp(1.3f) + AndroidUtilities.dp(7) * diff);
      onlineTextView[a].setTranslationX(-21 * AndroidUtilities.density * diff);
      onlineTextView[a].setTranslationY((float)Math.floor(avatarY) + AndroidUtilities.dp(24) + (float)Math.floor(11 * AndroidUtilities.density) * diff);
      float scale=1.0f + 0.12f * diff;
      nameTextView[a].setScaleX(scale);
      nameTextView[a].setScaleY(scale);
      if (a == 1 && !openAnimationInProgress) {
        int viewWidth;
        if (AndroidUtilities.isTablet()) {
          viewWidth=AndroidUtilities.dp(490);
        }
 else {
          viewWidth=AndroidUtilities.displaySize.x;
        }
        int buttonsWidth=AndroidUtilities.dp(118 + 8 + (40 + (callItem != null || editItem != null ? 48 : 0)));
        int minWidth=viewWidth - buttonsWidth;
        int width=(int)(viewWidth - buttonsWidth * Math.max(0.0f,1.0f - (diff != 1.0f ? diff * 0.15f / (1.0f - diff) : 1.0f)) - nameTextView[a].getTranslationX());
        float width2=nameTextView[a].getPaint().measureText(nameTextView[a].getText().toString()) * scale + nameTextView[a].getSideDrawablesSize();
        layoutParams=(FrameLayout.LayoutParams)nameTextView[a].getLayoutParams();
        if (width < width2) {
          layoutParams.width=Math.max(minWidth,(int)Math.ceil((width - AndroidUtilities.dp(24)) / (scale + (1.12f - scale) * 7.0f)));
        }
 else {
          layoutParams.width=(int)Math.ceil(width2);
        }
        layoutParams.width=(int)Math.min((viewWidth - nameTextView[a].getX()) / scale - AndroidUtilities.dp(8),layoutParams.width);
        nameTextView[a].setLayoutParams(layoutParams);
        width2=onlineTextView[a].getPaint().measureText(onlineTextView[a].getText().toString());
        layoutParams=(FrameLayout.LayoutParams)onlineTextView[a].getLayoutParams();
        layoutParams.rightMargin=(int)Math.ceil(onlineTextView[a].getTranslationX() + AndroidUtilities.dp(8) + AndroidUtilities.dp(40) * (1.0f - diff));
        if (width < width2) {
          layoutParams.width=(int)Math.ceil(width);
        }
 else {
          layoutParams.width=LayoutHelper.WRAP_CONTENT;
        }
        onlineTextView[a].setLayoutParams(layoutParams);
      }
    }
  }
}
