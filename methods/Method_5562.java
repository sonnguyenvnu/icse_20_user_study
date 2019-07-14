private void maybeInflateData(ParsableByteArray buffer){
  if (buffer.bytesLeft() > 0 && buffer.peekUnsignedByte() == INFLATE_HEADER) {
    if (inflater == null) {
      inflater=new Inflater();
    }
    if (Util.inflate(buffer,inflatedBuffer,inflater)) {
      buffer.reset(inflatedBuffer.data,inflatedBuffer.limit());
    }
  }
}
