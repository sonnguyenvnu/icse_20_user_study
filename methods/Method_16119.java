private Deque<String> getUsedHistoryQueue(){
  return ThreadLocalUtils.get(DefaultDataSourceSwitcher.class.getName() + "_queue",LinkedList::new);
}
