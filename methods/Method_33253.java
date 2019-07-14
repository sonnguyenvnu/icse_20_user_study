@Override protected void handleControlPropertyChanged(String p){
  super.handleControlPropertyChanged(p);
  if ("SHOWING".equals(p)) {
    if (getSkinnable().isShowing()) {
      show();
    }
 else     if (!popupContent.isCustomColorDialogShowing()) {
      hide();
    }
  }
 else   if ("VALUE".equals(p)) {
    updateColor();
  }
}
