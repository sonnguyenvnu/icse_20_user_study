public void checkMediaPaths(){
  cacheOutQueue.postRunnable(() -> {
    final SparseArray<File> paths=createMediaPaths();
    AndroidUtilities.runOnUIThread(() -> FileLoader.setMediaDirs(paths));
  }
);
}
