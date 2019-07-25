@Override public void end(){
  int textLength=getText().length();
  if (StyleAttributesUtil.isLastPositionAllowed(getStyle())) {
    if (textLength > 0 || StyleAttributesUtil.isFirstPositionAllowed(getStyle())) {
      setCaretPosition(getText().length());
    }
  }
 else {
    if (textLength > 0 && (textLength > 1 || StyleAttributesUtil.isFirstPositionAllowed(getStyle()))) {
      setCaretPosition(getText().length() - 1);
    }
  }
}
