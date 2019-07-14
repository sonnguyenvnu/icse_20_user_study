private void fileDidFailedLoad(final String location,int canceled){
  if (canceled == 1) {
    return;
  }
  imageLoadQueue.postRunnable(() -> {
    CacheImage img=imageLoadingByUrl.get(location);
    if (img != null) {
      img.setImageAndClear(null,null);
    }
  }
);
}
