public TokenCodecDecoder wrap(final DirectBuffer buffer,final int offset,final int actingBlockLength,final int actingVersion){
  if (buffer != this.buffer) {
    this.buffer=buffer;
  }
  this.offset=offset;
  this.actingBlockLength=actingBlockLength;
  this.actingVersion=actingVersion;
  limit(offset + actingBlockLength);
  return this;
}
