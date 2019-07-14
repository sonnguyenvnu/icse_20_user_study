@Override public boolean arePreferencesValid(){
  try {
    int i=Integer.valueOf(maximumDepthTextField.getText());
    return (i > 0) && (i <= MAX_VALUE);
  }
 catch (  NumberFormatException e) {
    assert ExceptionUtil.printStackTrace(e);
    return false;
  }
}
