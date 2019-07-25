public void tail(ProgressHandler handler,final String method,final URI uri) throws DockerException, InterruptedException {
  while (hasNextMessage(method,uri)) {
    if (Thread.interrupted()) {
      throw new InterruptedException();
    }
    handler.progress(nextMessage(method,uri));
  }
}
