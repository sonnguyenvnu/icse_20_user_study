private void loop(){
  try {
    WatchKey key=service.take();
    Collection<Path> paths=keys.get(key);
    for (    WatchEvent<?> event : from(key.pollEvents()).filter(isModifyEvent())) {
      final Path context=(Path)event.context();
      for (      Path path : from(paths).filter(isForPath(context))) {
        for (        Function<File,Void> listener : this.listeners.get(path)) {
          listener.apply(path.toFile());
        }
        break;
      }
    }
    key.reset();
  }
 catch (  ClosedWatchServiceException ignored) {
  }
catch (  InterruptedException e) {
    logger.error("Error happens",e);
  }
}
