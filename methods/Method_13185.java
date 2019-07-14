@Override public void loadPreferences(Map<String,String> preferences){
  String fontSize=preferences.get(FONT_SIZE_KEY);
  if (fontSize == null) {
    RSyntaxTextArea textArea=new RSyntaxTextArea();
    try {
      Theme theme=Theme.load(getClass().getClassLoader().getResourceAsStream("rsyntaxtextarea/themes/eclipse.xml"));
      theme.apply(textArea);
    }
 catch (    IOException e) {
      assert ExceptionUtil.printStackTrace(e);
    }
    fontSize=String.valueOf(textArea.getFont().getSize());
  }
  fontSizeTextField.setText(fontSize);
  fontSizeTextField.setCaretPosition(fontSizeTextField.getText().length());
}
