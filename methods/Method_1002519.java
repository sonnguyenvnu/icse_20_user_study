@Override public void flush(ChannelHandlerContext ctx) throws Exception {
  if (_currentReader != null) {
    _currentReader.flush();
  }
 else {
    ctx.flush();
  }
}
