public void start() throws Exception {
  super.start();
  if (use_gui) {
    discard_dialog=new DiscardDialog();
    discard_dialog.init();
  }
}
