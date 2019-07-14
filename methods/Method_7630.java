public View getButton(int type){
  if (buttonsLayout != null) {
    return buttonsLayout.findViewWithTag(type);
  }
  return null;
}
