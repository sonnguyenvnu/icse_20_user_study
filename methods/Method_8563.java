public void setAspectLock(boolean enabled){
  aspectRatioButton.setColorFilter(enabled ? new PorterDuffColorFilter(0xff51bdf3,PorterDuff.Mode.MULTIPLY) : null);
}
