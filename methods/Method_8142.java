@Override public void setPressed(boolean pressed){
  if (drawCheckRipple) {
    checkBox.setDrawRipple(pressed);
  }
  super.setPressed(pressed);
}
