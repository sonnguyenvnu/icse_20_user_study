@Override public void handle(SetCompression setCompression) throws Exception {
  server.getCh().setCompressionThreshold(setCompression.getThreshold());
}
