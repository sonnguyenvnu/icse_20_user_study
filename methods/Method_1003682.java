private void refresh() throws Exception {
  lock.lock();
  try {
    FileTime lastModifiedTime=Files.getLastModifiedTime(file);
    String content=Paths2.readText(file,StandardCharsets.UTF_8);
    if (lastModifiedTime.equals(lastModifiedHolder.get()) && content.equals(contentHolder.get())) {
      return;
    }
    T previous=delegateHolder.getAndSet(null);
    if (previous != null) {
      releaser.release(previous);
    }
    delegateHolder.set(producer.produce(file,content));
    this.lastModifiedHolder.set(lastModifiedTime);
    this.contentHolder.set(content);
  }
  finally {
    lock.unlock();
  }
}
