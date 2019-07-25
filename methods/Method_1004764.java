@Override public Object[] next(){
  if (!hasNext()) {
    throw new NoSuchElementException("No more documents available");
  }
  return batch.get(batchIndex++);
}
