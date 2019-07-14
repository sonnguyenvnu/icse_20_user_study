private PorterDuffColorFilter updateTintFilter(ColorStateList tint,PorterDuff.Mode tintMode){
  if (tint == null || tintMode == null) {
    return null;
  }
  final int color=tint.getColorForState(getState(),Color.TRANSPARENT);
  return new PorterDuffColorFilter(color,tintMode);
}
