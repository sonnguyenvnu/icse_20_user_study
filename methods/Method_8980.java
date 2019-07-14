public void setMiniIcon(int icon,boolean ifSame,boolean animated){
  if (icon != MediaActionDrawable.ICON_DOWNLOAD && icon != MediaActionDrawable.ICON_CANCEL && icon != MediaActionDrawable.ICON_NONE) {
    return;
  }
  if (ifSame && icon == miniMediaActionDrawable.getCurrentIcon()) {
    return;
  }
  miniMediaActionDrawable.setIcon(icon,animated);
  drawMiniIcon=icon != MediaActionDrawable.ICON_NONE || miniMediaActionDrawable.getTransitionProgress() < 1.0f;
  if (drawMiniIcon) {
    initMiniIcons();
  }
  if (!animated) {
    parent.invalidate();
  }
 else {
    invalidateParent();
  }
}
