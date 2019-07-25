private List<MessageExt> filter(List<MessageExt> msgs){
  if (isFirstSub && !properties.isEnableHisConsumer()) {
    msgs=msgs.stream().filter(item -> startTime - item.getBornTimestamp() < 0).collect(Collectors.toList());
  }
  if (isFirstSub && msgs.size() > 0) {
    isFirstSub=false;
  }
  return msgs;
}
