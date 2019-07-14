private void httpFileLoadError(final String location){
  imageLoadQueue.postRunnable(() -> {
    CacheImage img=imageLoadingByUrl.get(location);
    if (img == null) {
      return;
    }
    HttpImageTask oldTask=img.httpTask;
    img.httpTask=new HttpImageTask(oldTask.cacheImage,oldTask.imageSize);
    httpTasks.add(img.httpTask);
    runHttpTasks(false);
  }
);
}
