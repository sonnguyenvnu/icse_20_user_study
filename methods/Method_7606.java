public void redrawPopup(int color){
  if (popupLayout != null) {
    popupLayout.backgroundDrawable.setColorFilter(new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));
    popupLayout.invalidate();
  }
}
