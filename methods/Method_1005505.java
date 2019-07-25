@ReactMethod public void hide(){
  if (dialog == null) {
    return;
  }
  if (dialog.isShowing()) {
    dialog.dismiss();
  }
}
