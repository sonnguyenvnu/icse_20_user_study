public void syncWithAutoUpdate(){
  if (!getPopup().isShowing() && jfxDatePicker.isShowing()) {
    jfxDatePicker.hide();
  }
}
