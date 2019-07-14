private void setCompressItemEnabled(boolean enabled,boolean animated){
  if (compressItem == null) {
    return;
  }
  if (enabled && compressItem.getTag() != null || !enabled && compressItem.getTag() == null) {
    return;
  }
  compressItem.setTag(enabled ? 1 : null);
  compressItem.setEnabled(enabled);
  compressItem.setClickable(enabled);
  if (compressItemAnimation != null) {
    compressItemAnimation.cancel();
    compressItemAnimation=null;
  }
  if (animated) {
    compressItemAnimation=new AnimatorSet();
    compressItemAnimation.playTogether(ObjectAnimator.ofFloat(compressItem,View.ALPHA,enabled ? 1.0f : 0.5f));
    compressItemAnimation.setDuration(180);
    compressItemAnimation.setInterpolator(decelerateInterpolator);
    compressItemAnimation.start();
  }
 else {
    compressItem.setAlpha(enabled ? 1.0f : 0.5f);
  }
}
