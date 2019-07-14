public void onTextChange(){
  fontSizeTextField.setBackground(arePreferencesValid() ? defaultBackgroundColor : errorBackgroundColor);
  if (listener != null) {
    listener.preferencesPanelChanged(this);
  }
}
