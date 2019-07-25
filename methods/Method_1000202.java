private void reload(){
  parentContents=listFiles();
  MaterialDialog dialog=(MaterialDialog)getDialog();
  dialog.setTitle(parentFolder.getAbsolutePath());
  dialog.setItems((CharSequence[])getContentsArray());
}
