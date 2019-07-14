private long getWallPaperId(Object object){
  if (object instanceof TLRPC.TL_wallPaper) {
    return ((TLRPC.TL_wallPaper)object).id;
  }
 else   if (object instanceof ColorWallpaper) {
    return ((ColorWallpaper)object).id;
  }
 else   if (object instanceof FileWallpaper) {
    return ((FileWallpaper)object).id;
  }
 else {
    return 0;
  }
}
