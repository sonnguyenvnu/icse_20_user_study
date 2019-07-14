@Override public synchronized byte read(final int offset){
  Preconditions.checkState(!isClosed());
  Preconditions.checkArgument(offset >= 0);
  Preconditions.checkArgument(offset < mSize);
  return mBuffer.get(offset);
}
