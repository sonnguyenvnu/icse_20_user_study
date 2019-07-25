public TokenCodecEncoder signal(final SignalCodec value){
  buffer.putByte(offset + 20,(byte)value.value());
  return this;
}
