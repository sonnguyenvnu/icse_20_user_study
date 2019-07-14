public void unregister(final File file){
  Path directory=Files.directoryOf(file).toPath();
  Path path=file.toPath();
  if (!directoryToFiles.containsEntry(directory,path)) {
    return;
  }
  directoryToFiles.remove(directory,path);
  if (!directoryToFiles.containsKey(directory)) {
    WatchKey key=directoryToKey.remove(directory);
    if (key != null) {
      key.cancel();
    }
  }
  if (directoryToFiles.isEmpty()) {
    this.stop();
  }
}
