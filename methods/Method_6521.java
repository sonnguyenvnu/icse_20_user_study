private void artworkLoadError(final String location){
  imageLoadQueue.postRunnable(() -> {
    CacheImage img=imageLoadingByUrl.get(location);
    if (img == null) {
      return;
    }
    ArtworkLoadTask oldTask=img.artworkTask;
    img.artworkTask=new ArtworkLoadTask(oldTask.cacheImage);
    artworkTasks.add(img.artworkTask);
    runArtworkTasks(false);
  }
);
}
