protected void onTextChange(){
  filtersTextArea.setBackground(arePreferencesValid() ? defaultBackgroundColor : errorBackgroundColor);
  if (listener != null) {
    listener.preferencesPanelChanged(this);
  }
}
