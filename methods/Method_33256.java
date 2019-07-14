public void syncWithAutoUpdate(){
  if (!getPopup().isShowing() && getSkinnable().isShowing()) {
    getSkinnable().hide();
  }
}
