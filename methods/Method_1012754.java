@Override public void write(int b) throws IOException {
  boolean bb=b % 100000 == 0;
  WaitBufferedInputStream input=getCurrentInputStream();
  while (bb && ((input != null && (writeCount - input.getReadCount() > bufferOverflowWarning)) || (input == null && writeCount == bufferOverflowWarning))) {
    try {
      Thread.sleep(CHECK_INTERVAL);
    }
 catch (    InterruptedException e) {
    }
    input=getCurrentInputStream();
  }
  int mb=(int)(writeCount++ % maxMemorySize);
  if (buffer != null) {
    buffer[mb]=(byte)b;
    buffered=true;
    if (writeCount == INITIAL_BUFFER_SIZE) {
      buffer=growBuffer(buffer,maxMemorySize);
    }
    if (timeseek > 0 && writeCount > 19) {
      shiftByTimeSeek(mb,mb <= 20);
    }
    if (timeseek > 0 && writeCount > 10) {
      shiftSCRByTimeSeek(mb,(int)timeseek);
    }
  }
}
