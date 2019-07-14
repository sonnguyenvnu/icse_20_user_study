public String getRolloverText(InputEvent e){
  if (e.isShiftDown()) {
    return titleShift;
  }
 else   if (e.isAltDown()) {
    return titleAlt;
  }
  return title;
}
