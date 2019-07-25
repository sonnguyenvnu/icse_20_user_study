private static void xor(byte buffer[],TurningBytes turningBytes){
  for (int i=0; i < buffer.length; i++) {
    buffer[i]^=turningBytes.nextByte();
  }
}
