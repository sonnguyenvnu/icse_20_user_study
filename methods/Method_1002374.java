/** 
 * Retry last failed markUp or markDown operation if there is any. This method needs to be called whenever the zookeeper connection is lost and then back again(zk session is still valid).
 */
synchronized void retry(Callback<None> callback){
  if (!_pendingMarkDown.isEmpty() || !_pendingMarkUp.isEmpty()) {
    if (_isUp) {
      markUp(callback);
    }
 else {
      markDown(callback);
    }
  }
}
