@SuppressWarnings("unchecked") public void post(final Object event){
  final Lock lock=guard.readLock();
  lock.lock();
  try {
    final List<Listener> listeners=listenersByType.get(event.getClass());
    if (listeners == null) {
      throw new RuntimeException("unsupported event type " + event.getClass().getSimpleName());
    }
    for (    final Listener listener : listeners) {
      listener.onEvent(event);
    }
  }
  finally {
    lock.unlock();
  }
}
