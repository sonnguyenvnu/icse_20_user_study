@Override public boolean advancePeekPosition(int length,boolean allowEndOfInput) throws IOException, InterruptedException {
  ensureSpaceForPeek(length);
  int bytesPeeked=peekBufferLength - peekBufferPosition;
  while (bytesPeeked < length) {
    bytesPeeked=readFromDataSource(peekBuffer,peekBufferPosition,length,bytesPeeked,allowEndOfInput);
    if (bytesPeeked == C.RESULT_END_OF_INPUT) {
      return false;
    }
    peekBufferLength=peekBufferPosition + bytesPeeked;
  }
  peekBufferPosition+=length;
  return true;
}
