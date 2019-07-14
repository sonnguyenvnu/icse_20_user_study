private void applyViewsLayoutParams(int xOffset){
  FrameLayout.LayoutParams layoutParams;
  RelativeLayout.LayoutParams rLayoutParams;
  int widht=AndroidUtilities.displaySize.x - AndroidUtilities.dp(24);
  if (leftView != null) {
    layoutParams=(FrameLayout.LayoutParams)leftView.getLayoutParams();
    if (layoutParams.width != widht) {
      layoutParams.width=widht;
      leftView.setLayoutParams(layoutParams);
    }
    leftView.setTranslationX(-widht + xOffset);
  }
  if (leftButtonsView != null) {
    leftButtonsView.setTranslationX(-widht + xOffset);
  }
  if (centerView != null) {
    layoutParams=(FrameLayout.LayoutParams)centerView.getLayoutParams();
    if (layoutParams.width != widht) {
      layoutParams.width=widht;
      centerView.setLayoutParams(layoutParams);
    }
    centerView.setTranslationX(xOffset);
  }
  if (centerButtonsView != null) {
    centerButtonsView.setTranslationX(xOffset);
  }
  if (rightView != null) {
    layoutParams=(FrameLayout.LayoutParams)rightView.getLayoutParams();
    if (layoutParams.width != widht) {
      layoutParams.width=widht;
      rightView.setLayoutParams(layoutParams);
    }
    rightView.setTranslationX(widht + xOffset);
  }
  if (rightButtonsView != null) {
    rightButtonsView.setTranslationX(widht + xOffset);
  }
  messageContainer.invalidate();
}
