private Collection<ConfigStatusMessage> filter(Collection<?> filter,Predicate<ConfigStatusMessage> predicate){
  if (filter.isEmpty()) {
    return getConfigStatusMessages();
  }
  return filterConfigStatusMessages(getConfigStatusMessages(),predicate);
}
