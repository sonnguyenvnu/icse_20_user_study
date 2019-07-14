public void syncWithAutoUpdate(){
  if (!getPopup().isShowing() && jfxTimePicker.isShowing()) {
    jfxTimePicker.hide();
  }
}
