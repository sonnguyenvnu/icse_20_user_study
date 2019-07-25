public byte value(){
  byte value=(byte)(precedence.value() << 5);
  if (lowDelay) {
    value=(byte)(value | 0x10);
  }
  if (highThroughput) {
    value=(byte)(value | 0x08);
  }
  if (highReliability) {
    value=(byte)(value | 0x04);
  }
  if (seventhBit) {
    value=(byte)(value | 0x02);
  }
  if (eighthBit) {
    value=(byte)(value | 0x01);
  }
  return value;
}
