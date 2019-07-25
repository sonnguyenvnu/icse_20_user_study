@Override public void process(List<ProduceMessage> list){
  long start=System.currentTimeMillis();
  try {
    Collection<MessageSenderGroup> messages=groupBy(list);
    for (    MessageSenderGroup group : messages) {
      group.send();
    }
  }
  finally {
    timer.update(System.currentTimeMillis() - start,TimeUnit.MILLISECONDS);
  }
}
