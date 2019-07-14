private void drawImageIconPadding(){
  if (iconPadding == -1 || iconPadding == 0) {
    ivProgressIcon.setPadding(iconPaddingLeft,iconPaddingTop,iconPaddingRight,iconPaddingBottom);
  }
 else {
    ivProgressIcon.setPadding(iconPadding,iconPadding,iconPadding,iconPadding);
  }
  ivProgressIcon.invalidate();
}
