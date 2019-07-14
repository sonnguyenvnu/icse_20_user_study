public boolean setText(CharSequence value,boolean force){
  if (text == null && value == null || !force && text != null && text.equals(value)) {
    return false;
  }
  text=value;
  scrollingOffset=0;
  currentScrollDelay=SCROLL_DELAY_MS;
  recreateLayoutMaybe();
  return true;
}
