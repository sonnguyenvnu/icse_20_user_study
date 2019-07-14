public static int getThumbForNameOrMime(String name,String mime,boolean media){
  if (name != null && name.length() != 0) {
    int color=-1;
    if (name.contains(".doc") || name.contains(".txt") || name.contains(".psd")) {
      color=0;
    }
 else     if (name.contains(".xls") || name.contains(".csv")) {
      color=1;
    }
 else     if (name.contains(".pdf") || name.contains(".ppt") || name.contains(".key")) {
      color=2;
    }
 else     if (name.contains(".zip") || name.contains(".rar") || name.contains(".ai") || name.contains(".mp3") || name.contains(".mov") || name.contains(".avi")) {
      color=3;
    }
    if (color == -1) {
      int idx;
      String ext=(idx=name.lastIndexOf('.')) == -1 ? "" : name.substring(idx + 1);
      if (ext.length() != 0) {
        color=ext.charAt(0) % documentIcons.length;
      }
 else {
        color=name.charAt(0) % documentIcons.length;
      }
    }
    return media ? documentMediaIcons[color] : documentIcons[color];
  }
  return media ? documentMediaIcons[0] : documentIcons[0];
}
