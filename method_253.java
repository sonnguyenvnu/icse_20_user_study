private void _XXXXX_(){
  if (null != accessControlManager) {
    accessControlManager.close();
    LOG.info("Access Control Manager Stopped.");
  }
  if (null != allocator) {
    Utils.closeQuietly(allocator);
    LOG.info("Ledger Allocator stopped.");
  }
  Utils.close(writerStreamMetadataStore);
  Utils.close(readerStreamMetadataStore);
  writerBKC.close();
  readerBKC.close();
  writerZKC.close();
  readerZKC.close();
  eventLoopGroup.shutdownGracefully();
  LOG.info("Release external resources used by channel factory.");
  requestTimer.stop();
  LOG.info("Stopped request timer");
}