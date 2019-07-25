public byte value(){
  byte value=(byte)(precedence.value() << 5);
  value=(byte)(value | tos.value() << 1);
  if (mbz) {
    value=(byte)(value | 0x01);
  }
  return value;
}
