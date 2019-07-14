@Override public synchronized byte read(int offset){
  ensureValid();
  Preconditions.checkArgument(offset >= 0);
  Preconditions.checkArgument(offset < mSize);
  return mBufRef.get().read(offset);
}
