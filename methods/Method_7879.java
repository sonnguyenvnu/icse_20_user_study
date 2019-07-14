private void checkProgress(int a,boolean animated){
  if (currentFileNames[a] != null) {
    int index=currentIndex;
    if (a == 1) {
      index+=1;
    }
 else     if (a == 2) {
      index-=1;
    }
    File f=getMediaFile(index);
    boolean isVideo=isMediaVideo(index);
    if (f != null && f.exists()) {
      if (isVideo) {
        radialProgressViews[a].setBackgroundState(3,animated);
      }
 else {
        radialProgressViews[a].setBackgroundState(-1,animated);
      }
    }
 else {
      if (isVideo) {
        if (!FileLoader.getInstance(currentAccount).isLoadingFile(currentFileNames[a])) {
          radialProgressViews[a].setBackgroundState(2,false);
        }
 else {
          radialProgressViews[a].setBackgroundState(1,false);
        }
      }
 else {
        radialProgressViews[a].setBackgroundState(0,animated);
      }
      Float progress=ImageLoader.getInstance().getFileProgress(currentFileNames[a]);
      if (progress == null) {
        progress=0.0f;
      }
      radialProgressViews[a].setProgress(progress,false);
    }
    if (a == 0) {
      canZoom=(currentFileNames[0] != null && !isVideo && radialProgressViews[0].backgroundState != 0);
    }
  }
 else {
    radialProgressViews[a].setBackgroundState(-1,animated);
  }
}
