@Override public void home(){
  int textLength=getText().length();
  if (StyleAttributesUtil.isFirstPositionAllowed(getStyle())) {
    if (textLength > 0 || StyleAttributesUtil.isLastPositionAllowed(getStyle())) {
      setCaretPosition(0);
    }
  }
 else {
    if (textLength > 0 && (textLength > 1 || StyleAttributesUtil.isLastPositionAllowed(getStyle()))) {
      setCaretPosition(1);
    }
  }
}
