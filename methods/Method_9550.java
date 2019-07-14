private void fixLayout(){
  if (avatarContainer != null) {
    avatarContainer.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(){
      @Override public boolean onPreDraw(){
        if (avatarContainer != null) {
          avatarContainer.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        int padding=(ActionBar.getCurrentActionBarHeight() - AndroidUtilities.dp(48)) / 2;
        avatarContainer.setPadding(avatarContainer.getPaddingLeft(),padding,avatarContainer.getPaddingRight(),padding);
        return true;
      }
    }
);
  }
  if (messageContainer != null) {
    messageContainer.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(){
      @Override public boolean onPreDraw(){
        messageContainer.getViewTreeObserver().removeOnPreDrawListener(this);
        if (!checkTransitionAnimation() && !startedMoving) {
          ViewGroup.MarginLayoutParams layoutParams=(ViewGroup.MarginLayoutParams)messageContainer.getLayoutParams();
          layoutParams.topMargin=ActionBar.getCurrentActionBarHeight();
          layoutParams.bottomMargin=AndroidUtilities.dp(48);
          layoutParams.width=ViewGroup.MarginLayoutParams.MATCH_PARENT;
          layoutParams.height=ViewGroup.MarginLayoutParams.MATCH_PARENT;
          messageContainer.setLayoutParams(layoutParams);
          applyViewsLayoutParams(0);
        }
        return true;
      }
    }
);
  }
}
