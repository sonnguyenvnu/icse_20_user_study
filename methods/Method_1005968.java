private DuplexChannel connect(final Bootstrap bootstrap) throws InterruptedException {
  return nettyInitializer.connect(bootstrap);
}
