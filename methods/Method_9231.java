public void loadRecentFiles(){
  try {
    File[] files=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).listFiles();
    for (int a=0; a < files.length; a++) {
      File file=files[a];
      if (file.isDirectory()) {
        continue;
      }
      ListItem item=new ListItem();
      item.title=file.getName();
      item.file=file;
      String fname=file.getName();
      String[] sp=fname.split("\\.");
      item.ext=sp.length > 1 ? sp[sp.length - 1] : "?";
      item.subtitle=AndroidUtilities.formatFileSize(file.length());
      fname=fname.toLowerCase();
      if (fname.endsWith(".jpg") || fname.endsWith(".png") || fname.endsWith(".gif") || fname.endsWith(".jpeg")) {
        item.thumb=file.getAbsolutePath();
      }
      recentItems.add(item);
    }
    Collections.sort(recentItems,(o1,o2) -> {
      long lm=o1.file.lastModified();
      long rm=o2.file.lastModified();
      if (lm == rm) {
        return 0;
      }
 else       if (lm > rm) {
        return -1;
      }
 else {
        return 1;
      }
    }
);
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
