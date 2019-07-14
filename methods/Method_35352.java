protected void resizeDialogSize(){
  Window window=getDialog().getWindow();
  Point size=new Point();
  window.getWindowManager().getDefaultDisplay().getSize(size);
  window.setLayout((int)(size.x * DIALOG_WIDTH_PROPORTION),WindowManager.LayoutParams.WRAP_CONTENT);
}
