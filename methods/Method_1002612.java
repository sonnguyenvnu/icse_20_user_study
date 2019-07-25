public SignalCodec signal(){
  return SignalCodec.get(((short)(buffer.getByte(offset + 20) & 0xFF)));
}
