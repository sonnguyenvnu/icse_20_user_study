public void setPatterns(ArrayList<Object> arrayList){
  patterns=arrayList;
  if (currentWallpaper instanceof WallpapersListActivity.ColorWallpaper) {
    WallpapersListActivity.ColorWallpaper wallPaper=(WallpapersListActivity.ColorWallpaper)currentWallpaper;
    if (wallPaper.patternId != 0) {
      for (int a=0, N=patterns.size(); a < N; a++) {
        TLRPC.TL_wallPaper pattern=(TLRPC.TL_wallPaper)patterns.get(a);
        if (pattern.id == wallPaper.patternId) {
          selectedPattern=pattern;
          break;
        }
      }
      currentIntensity=wallPaper.intensity;
    }
  }
}
