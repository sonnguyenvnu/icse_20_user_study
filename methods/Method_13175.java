public void onTextChange(){
  maximumDepthTextField.setBackground(arePreferencesValid() ? defaultBackgroundColor : errorBackgroundColor);
  if (listener != null) {
    listener.preferencesPanelChanged(this);
  }
}
