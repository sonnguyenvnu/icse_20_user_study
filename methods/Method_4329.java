@Override public final I dequeueInputBuffer() throws E {
synchronized (lock) {
    maybeThrowException();
    Assertions.checkState(dequeuedInputBuffer == null);
    dequeuedInputBuffer=availableInputBufferCount == 0 ? null : availableInputBuffers[--availableInputBufferCount];
    return dequeuedInputBuffer;
  }
}
