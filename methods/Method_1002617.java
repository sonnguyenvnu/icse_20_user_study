public TokenCodecEncoder wrap(final MutableDirectBuffer buffer,final int offset){
  if (buffer != this.buffer) {
    this.buffer=buffer;
  }
  this.offset=offset;
  limit(offset + BLOCK_LENGTH);
  return this;
}
