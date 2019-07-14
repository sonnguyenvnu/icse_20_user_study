@Override public List<Object> getResult() throws Exception {
  while (transactionJobOverCounter.get() != transactionJobNumber.get() && failCounter.get() == 0) {
    Thread.sleep(50);
  }
  countDownLatch.countDown();
  List<Object> results=futures.stream().map(this::getValue).collect(Collectors.toList());
  if (!exceptions.isEmpty()) {
    throw new AsyncJobException(exceptions);
  }
  return results;
}
