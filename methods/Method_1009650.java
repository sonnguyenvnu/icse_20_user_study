@Override public void invoke(IN value) throws Exception {
  int number;
synchronized (this) {
    if (null != value) {
      incomingList.add(value);
    }
    number=incomingList.size();
  }
  if (number == batchSize) {
    flush();
  }
}
