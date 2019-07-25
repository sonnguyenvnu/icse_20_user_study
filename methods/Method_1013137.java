public final void unread(){
  if (fOffset == 0) {
    if (fBufferOffset == fRangeOffset) {
    }
 else {
      updateBuffer(fBufferOffset - fBuffer.length);
      fOffset=fBuffer.length - 1;
    }
  }
 else {
    --fOffset;
  }
}
