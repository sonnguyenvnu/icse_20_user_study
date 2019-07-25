@Override public void progress(ProgressMessage message) throws DockerException {
  if (message.error() != null) {
    throw new ImagePushFailedException(image,message.toString());
  }
  log.info("push {}: {}",image,message);
}
