public void preferencesChanged(Map<String,String> preferences){
  String fontSize=preferences.get(FONT_SIZE_KEY);
  if (fontSize != null) {
    try {
      textArea.setFont(textArea.getFont().deriveFont(Float.parseFloat(fontSize)));
    }
 catch (    Exception e) {
      assert ExceptionUtil.printStackTrace(e);
    }
  }
  this.preferences=preferences;
}
