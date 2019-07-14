private Deque<String> getUsedHistoryQueue(){
  return ThreadLocalUtils.get(DefaultDatabaseSwitcher.class.getName() + "_queue",LinkedList::new);
}
