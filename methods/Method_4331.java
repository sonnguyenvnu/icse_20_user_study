@Override public final O dequeueOutputBuffer() throws E {
synchronized (lock) {
    maybeThrowException();
    if (queuedOutputBuffers.isEmpty()) {
      return null;
    }
    return queuedOutputBuffers.removeFirst();
  }
}
