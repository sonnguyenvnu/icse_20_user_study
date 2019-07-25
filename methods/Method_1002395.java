private List<PropertyEventSubscriber<T>> subscribers(String prop){
  List<PropertyEventSubscriber<T>> subscribers=_subscribers.get(prop);
  if (subscribers == null) {
    return _allPropertySubscribers;
  }
  if (_allPropertySubscribers.isEmpty()) {
    return subscribers;
  }
  List<PropertyEventSubscriber<T>> all=new ArrayList<PropertyEventSubscriber<T>>(subscribers.size() + _allPropertySubscribers.size());
  all.addAll(_allPropertySubscribers);
  all.addAll(subscribers);
  return all;
}
