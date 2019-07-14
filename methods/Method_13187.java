@Override public boolean arePreferencesValid(){
  try {
    int i=Integer.valueOf(fontSizeTextField.getText());
    return (i >= MIN_VALUE) && (i <= MAX_VALUE);
  }
 catch (  NumberFormatException e) {
    assert ExceptionUtil.printStackTrace(e);
    return false;
  }
}
