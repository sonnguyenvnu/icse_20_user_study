@Override protected void onPostExecute(CopyNode copyFolder){
  super.onPostExecute(copyFolder);
  if (openMode == OpenMode.OTG || openMode == OpenMode.GDRIVE || openMode == OpenMode.DROPBOX || openMode == OpenMode.BOX || openMode == OpenMode.ONEDRIVE || openMode == OpenMode.ROOT) {
    startService(filesToCopy,path,openMode);
  }
 else {
    if (copyFolder == null) {
      dialog.dismiss();
      return;
    }
    onEndDialog(null,null,null);
  }
  dialog.dismiss();
}
