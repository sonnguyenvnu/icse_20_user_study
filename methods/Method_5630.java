private static boolean maybeSkipComment(ParsableByteArray input){
  int position=input.getPosition();
  int limit=input.limit();
  byte[] data=input.data;
  if (position + 2 <= limit && data[position++] == '/' && data[position++] == '*') {
    while (position + 1 < limit) {
      char skippedChar=(char)data[position++];
      if (skippedChar == '*') {
        if (((char)data[position]) == '/') {
          position++;
          limit=position;
        }
      }
    }
    input.skipBytes(limit - input.getPosition());
    return true;
  }
  return false;
}
