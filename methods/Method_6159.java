public static String getWallPaperUrl(Object object,int currentAccount){
  String link;
  if (object instanceof TLRPC.TL_wallPaper) {
    TLRPC.TL_wallPaper wallPaper=(TLRPC.TL_wallPaper)object;
    link="https://" + MessagesController.getInstance(currentAccount).linkPrefix + "/bg/" + wallPaper.slug;
    StringBuilder modes=new StringBuilder();
    if (wallPaper.settings != null) {
      if (wallPaper.settings.blur) {
        modes.append("blur");
      }
      if (wallPaper.settings.motion) {
        if (modes.length() > 0) {
          modes.append("+");
        }
        modes.append("motion");
      }
    }
    if (modes.length() > 0) {
      link+="?mode=" + modes.toString();
    }
  }
 else   if (object instanceof WallpapersListActivity.ColorWallpaper) {
    WallpapersListActivity.ColorWallpaper wallPaper=(WallpapersListActivity.ColorWallpaper)object;
    String color=String.format("%02x%02x%02x",(byte)(wallPaper.color >> 16) & 0xff,(byte)(wallPaper.color >> 8) & 0xff,(byte)(wallPaper.color & 0xff)).toLowerCase();
    if (wallPaper.pattern != null) {
      link="https://" + MessagesController.getInstance(currentAccount).linkPrefix + "/bg/" + wallPaper.pattern.slug + "?intensity=" + (int)(wallPaper.intensity * 100) + "&bg_color=" + color;
    }
 else {
      link="https://" + MessagesController.getInstance(currentAccount).linkPrefix + "/bg/" + color;
    }
  }
 else {
    link=null;
  }
  return link;
}
