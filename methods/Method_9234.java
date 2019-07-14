private boolean listFiles(File dir){
  if (!dir.canRead()) {
    if (dir.getAbsolutePath().startsWith(Environment.getExternalStorageDirectory().toString()) || dir.getAbsolutePath().startsWith("/sdcard") || dir.getAbsolutePath().startsWith("/mnt/sdcard")) {
      if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && !Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
        currentDir=dir;
        items.clear();
        String state=Environment.getExternalStorageState();
        if (Environment.MEDIA_SHARED.equals(state)) {
          emptyView.setText(LocaleController.getString("UsbActive",R.string.UsbActive));
        }
 else {
          emptyView.setText(LocaleController.getString("NotMounted",R.string.NotMounted));
        }
        AndroidUtilities.clearDrawableAnimation(listView);
        scrolling=true;
        listAdapter.notifyDataSetChanged();
        return true;
      }
    }
    showErrorBox(LocaleController.getString("AccessError",R.string.AccessError));
    return false;
  }
  File[] files;
  try {
    files=dir.listFiles();
  }
 catch (  Exception e) {
    showErrorBox(e.getLocalizedMessage());
    return false;
  }
  if (files == null) {
    showErrorBox(LocaleController.getString("UnknownError",R.string.UnknownError));
    return false;
  }
  currentDir=dir;
  items.clear();
  Arrays.sort(files,(lhs,rhs) -> {
    if (lhs.isDirectory() != rhs.isDirectory()) {
      return lhs.isDirectory() ? -1 : 1;
    }
    return lhs.getName().compareToIgnoreCase(rhs.getName());
  }
);
  for (int a=0; a < files.length; a++) {
    File file=files[a];
    if (file.getName().indexOf('.') == 0) {
      continue;
    }
    ListItem item=new ListItem();
    item.title=file.getName();
    item.file=file;
    if (file.isDirectory()) {
      item.icon=R.drawable.ic_directory;
      item.subtitle=LocaleController.getString("Folder",R.string.Folder);
    }
 else {
      String fname=file.getName();
      String[] sp=fname.split("\\.");
      item.ext=sp.length > 1 ? sp[sp.length - 1] : "?";
      item.subtitle=AndroidUtilities.formatFileSize(file.length());
      fname=fname.toLowerCase();
      if (fname.endsWith(".jpg") || fname.endsWith(".png") || fname.endsWith(".gif") || fname.endsWith(".jpeg")) {
        item.thumb=file.getAbsolutePath();
      }
    }
    items.add(item);
  }
  ListItem item=new ListItem();
  item.title="..";
  if (history.size() > 0) {
    HistoryEntry entry=history.get(history.size() - 1);
    if (entry.dir == null) {
      item.subtitle=LocaleController.getString("Folder",R.string.Folder);
    }
 else {
      item.subtitle=entry.dir.toString();
    }
  }
 else {
    item.subtitle=LocaleController.getString("Folder",R.string.Folder);
  }
  item.icon=R.drawable.ic_directory;
  item.file=null;
  items.add(0,item);
  AndroidUtilities.clearDrawableAnimation(listView);
  scrolling=true;
  listAdapter.notifyDataSetChanged();
  return true;
}
