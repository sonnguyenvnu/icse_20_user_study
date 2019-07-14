@Override public final void queueInputBuffer(I inputBuffer) throws E {
synchronized (lock) {
    maybeThrowException();
    Assertions.checkArgument(inputBuffer == dequeuedInputBuffer);
    queuedInputBuffers.addLast(inputBuffer);
    maybeNotifyDecodeLoop();
    dequeuedInputBuffer=null;
  }
}
