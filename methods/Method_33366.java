@Override protected void handleControlPropertyChanged(String p){
  if ("DEFAULT_COLOR".equals(p)) {
    ((JFXTextField)getEditor()).setFocusColor(jfxTimePicker.getDefaultColor());
  }
 else   if ("CONVERTER".equals(p)) {
    updateDisplayNode();
  }
 else   if ("EDITOR".equals(p)) {
    getEditableInputNode();
  }
 else   if ("SHOWING".equals(p)) {
    if (jfxTimePicker.isShowing()) {
      show();
    }
 else {
      hide();
    }
  }
 else   if ("VALUE".equals(p)) {
    updateDisplayNode();
    jfxTimePicker.fireEvent(new ActionEvent());
  }
 else {
    super.handleControlPropertyChanged(p);
  }
}
