@Override public Cancellable get(Callback<T> callback){
  ArgumentUtil.notNull(callback,"callback");
  final TimeTrackingCallback timeTrackingCallback=new TimeTrackingCallback(callback);
  final LinkedDeque.Node<Callback<T>> node;
  T item=null;
  boolean create=false;
  while (true) {
    final State state;
synchronized (_lock) {
      state=_state;
      if (state == State.RUNNING) {
        item=_item.get();
        if (item == null) {
          node=_waiters.size() < _maxWaiters ? _waiters.addLastNode(timeTrackingCallback) : null;
          if (_isCreateInProgress) {
            LOG.debug("{}: item creation is in progress",_name);
          }
 else {
            _isCreateInProgress=true;
            create=true;
          }
          break;
        }
        _checkedOut++;
        _statsTracker.sampleMaxCheckedOut();
      }
    }
    if (state != State.RUNNING) {
      timeTrackingCallback.onError(new IllegalStateException(_name + " is " + _state));
      return () -> false;
    }
    if (_lifecycle.validateGet(item)) {
      timeTrackingCallback.onSuccess(item);
      return () -> false;
    }
    boolean disposed;
synchronized (_lock) {
      disposed=doDispose(item);
    }
    if (disposed) {
      doDestroy(item,BAD,() -> {
      }
);
    }
  }
  if (node == null) {
    timeTrackingCallback.onError(new SizeLimitExceededException("AsyncPool " + _name + " reached maximum waiter size: " + _maxWaiters));
    return () -> false;
  }
  if (create) {
    doCreate();
  }
  return () -> {
synchronized (_lock) {
      return _waiters.removeNode(node) != null;
    }
  }
;
}
