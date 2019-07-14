public void setSelectorDrawableColor(int color){
  if (selectorDrawable != null) {
    selectorDrawable.setCallback(null);
  }
  selectorDrawable=Theme.getSelectorDrawable(color,false);
  selectorDrawable.setCallback(this);
}
