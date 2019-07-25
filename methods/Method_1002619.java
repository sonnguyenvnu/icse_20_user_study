public TokenCodecEncoder presence(final PresenceCodec value){
  buffer.putByte(offset + 23,(byte)value.value());
  return this;
}
