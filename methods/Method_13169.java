@Override public void loadPreferences(Map<String,String> preferences){
  String preference=preferences.get(MAXIMUM_DEPTH_KEY);
  maximumDepthTextField.setText((preference != null) ? preference : "15");
  maximumDepthTextField.setCaretPosition(maximumDepthTextField.getText().length());
}
