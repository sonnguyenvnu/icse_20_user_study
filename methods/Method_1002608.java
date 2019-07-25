public MessageHeaderDecoder wrap(final DirectBuffer buffer,final int offset){
  if (buffer != this.buffer) {
    this.buffer=buffer;
  }
  this.offset=offset;
  return this;
}
