public static RenameDialog create(){
  RenameDialog dialog=new RenameDialog("Enter new file name ","Rename file ");
  dialog.setKeyReleaseEvent("^[^\\\\/:?*\"<>|]+$");
  return dialog;
}
