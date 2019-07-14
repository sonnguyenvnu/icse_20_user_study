/** 
 * Pops the passed  {@link Controller} from the backstack
 * @param controller The controller that should be popped from this Router
 * @return Whether or not this Router still has controllers remaining on it after popping.
 */
@UiThread public boolean popController(@NonNull Controller controller){
  ThreadUtils.ensureMainThread();
  RouterTransaction topTransaction=backstack.peek();
  boolean poppingTopController=topTransaction != null && topTransaction.controller == controller;
  if (poppingTopController) {
    trackDestroyingController(backstack.pop());
    performControllerChange(backstack.peek(),topTransaction,false);
  }
 else {
    RouterTransaction removedTransaction=null;
    RouterTransaction nextTransaction=null;
    Iterator<RouterTransaction> iterator=backstack.iterator();
    ControllerChangeHandler topPushHandler=topTransaction != null ? topTransaction.pushChangeHandler() : null;
    final boolean needsNextTransactionAttach=topPushHandler != null ? !topPushHandler.removesFromViewOnPush() : false;
    while (iterator.hasNext()) {
      RouterTransaction transaction=iterator.next();
      if (transaction.controller == controller) {
        if (controller.isAttached()) {
          trackDestroyingController(transaction);
        }
        iterator.remove();
        removedTransaction=transaction;
      }
 else       if (removedTransaction != null) {
        if (needsNextTransactionAttach && !transaction.controller.isAttached()) {
          nextTransaction=transaction;
        }
        break;
      }
    }
    if (removedTransaction != null) {
      performControllerChange(nextTransaction,removedTransaction,false);
    }
  }
  if (popsLastView) {
    return topTransaction != null;
  }
 else {
    return !backstack.isEmpty();
  }
}
