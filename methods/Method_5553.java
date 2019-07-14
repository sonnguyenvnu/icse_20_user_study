/** 
 * Reads a value from the provided buffer consisting of zero or more 0xFF bytes followed by a terminating byte not equal to 0xFF. The returned value is ((0xFF * N) + T), where N is the number of 0xFF bytes and T is the value of the terminating byte.
 * @param buffer The buffer from which to read the value.
 * @return The read value, or -1 if the end of the buffer is reached before a value is read.
 */
private static int readNon255TerminatedValue(ParsableByteArray buffer){
  int b;
  int value=0;
  do {
    if (buffer.bytesLeft() == 0) {
      return -1;
    }
    b=buffer.readUnsignedByte();
    value+=b;
  }
 while (b == 0xFF);
  return value;
}
