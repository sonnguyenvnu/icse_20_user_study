@Override public void progress(ProgressMessage message) throws DockerException {
  if (message.error() != null) {
    if (message.error().contains("404") || message.error().contains("not found")) {
      throw new ImageNotFoundException(image,message.toString());
    }
 else {
      throw new ImagePullFailedException(image,message.toString());
    }
  }
  log.info("pull {}: {}",image,message);
}
