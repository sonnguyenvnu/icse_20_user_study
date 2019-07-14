private static long readUnsignedBackward(ReadBuffer in){
  int position=in.getPosition();
  int numBytes=0;
  long value=0;
  long b;
  while (true) {
    position--;
    b=in.getByte(position);
    if (b < 0) {
      value=value | ((b & 0x0F) << (7 * numBytes));
      assert ((b >>> 4) & 7) + 3 == numBytes + 1 : b + " vs " + numBytes;
      break;
    }
    value=value | (b << (7 * numBytes));
    numBytes++;
  }
  in.movePositionTo(position);
  return value;
}
