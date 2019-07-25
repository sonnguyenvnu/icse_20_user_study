@Override public void seek(long position) throws IOException {
  long delta=position - position();
  if ((delta > 0 && delta <= buffer.remaining()) || (delta < 0 && -delta <= buffer.position())) {
    buffer.position(buffer.position() + (int)delta);
  }
 else {
    channel.position(offset + position);
    buffer.position(buffer.limit());
  }
}
