@Override public void handle(SetCompression setCompression) throws Exception {
  ch.setCompressionThreshold(setCompression.getThreshold());
}
