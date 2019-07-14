public void setIconColor(int color){
  iconView.setColorFilter(new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));
  if (clearButton != null) {
    clearButton.setColorFilter(new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));
  }
}
