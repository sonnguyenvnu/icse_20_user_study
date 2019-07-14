/** 
 * Sets the backstack, transitioning from the current top controller to the top of the new stack (if different) using the passed  {@link ControllerChangeHandler}
 * @param newBackstack  The new backstack
 * @param changeHandler An optional change handler to be used to handle the root view of transition
 */
@SuppressWarnings("WeakerAccess") @UiThread public void setBackstack(@NonNull List<RouterTransaction> newBackstack,@Nullable ControllerChangeHandler changeHandler){
  ThreadUtils.ensureMainThread();
  List<RouterTransaction> oldTransactions=getBackstack();
  List<RouterTransaction> oldVisibleTransactions=getVisibleTransactions(backstack.iterator());
  removeAllExceptVisibleAndUnowned();
  ensureOrderedTransactionIndices(newBackstack);
  ensureNoDuplicateControllers(newBackstack);
  backstack.setBackstack(newBackstack);
  List<RouterTransaction> transactionsToBeRemoved=new ArrayList<>();
  for (  RouterTransaction oldTransaction : oldTransactions) {
    boolean contains=false;
    for (    RouterTransaction newTransaction : newBackstack) {
      if (oldTransaction.controller == newTransaction.controller) {
        contains=true;
        break;
      }
    }
    if (!contains) {
      oldTransaction.controller.isBeingDestroyed=true;
      transactionsToBeRemoved.add(oldTransaction);
    }
  }
  Iterator<RouterTransaction> backstackIterator=backstack.reverseIterator();
  while (backstackIterator.hasNext()) {
    RouterTransaction transaction=backstackIterator.next();
    transaction.onAttachedToRouter();
    setControllerRouter(transaction.controller);
  }
  if (newBackstack.size() > 0) {
    List<RouterTransaction> reverseNewBackstack=new ArrayList<>(newBackstack);
    Collections.reverse(reverseNewBackstack);
    List<RouterTransaction> newVisibleTransactions=getVisibleTransactions(reverseNewBackstack.iterator());
    boolean newRootRequiresPush=!(newVisibleTransactions.size() > 0 && oldTransactions.contains(newVisibleTransactions.get(0)));
    boolean visibleTransactionsChanged=!backstacksAreEqual(newVisibleTransactions,oldVisibleTransactions);
    if (visibleTransactionsChanged) {
      RouterTransaction oldRootTransaction=oldVisibleTransactions.size() > 0 ? oldVisibleTransactions.get(0) : null;
      RouterTransaction newRootTransaction=newVisibleTransactions.get(0);
      if (oldRootTransaction == null || oldRootTransaction.controller != newRootTransaction.controller) {
        if (oldRootTransaction != null) {
          ControllerChangeHandler.completeHandlerImmediately(oldRootTransaction.controller.getInstanceId());
        }
        performControllerChange(newRootTransaction,oldRootTransaction,newRootRequiresPush,changeHandler);
      }
      for (int i=oldVisibleTransactions.size() - 1; i > 0; i--) {
        RouterTransaction transaction=oldVisibleTransactions.get(i);
        if (!newVisibleTransactions.contains(transaction)) {
          ControllerChangeHandler localHandler=changeHandler != null ? changeHandler.copy() : new SimpleSwapChangeHandler();
          localHandler.setForceRemoveViewOnPush(true);
          ControllerChangeHandler.completeHandlerImmediately(transaction.controller.getInstanceId());
          performControllerChange(null,transaction,newRootRequiresPush,localHandler);
        }
      }
      for (int i=1; i < newVisibleTransactions.size(); i++) {
        RouterTransaction transaction=newVisibleTransactions.get(i);
        if (!oldVisibleTransactions.contains(transaction)) {
          performControllerChange(transaction,newVisibleTransactions.get(i - 1),true,transaction.pushChangeHandler());
        }
      }
    }
  }
 else {
    for (int i=oldVisibleTransactions.size() - 1; i >= 0; i--) {
      RouterTransaction transaction=oldVisibleTransactions.get(i);
      ControllerChangeHandler localHandler=changeHandler != null ? changeHandler.copy() : new SimpleSwapChangeHandler();
      ControllerChangeHandler.completeHandlerImmediately(transaction.controller.getInstanceId());
      performControllerChange(null,transaction,false,localHandler);
    }
  }
  for (  RouterTransaction removedTransaction : transactionsToBeRemoved) {
    boolean willBeRemoved=false;
    for (    ChangeTransaction pendingTransaction : pendingControllerChanges) {
      if (pendingTransaction.from == removedTransaction.controller) {
        willBeRemoved=true;
      }
    }
    if (!willBeRemoved) {
      removedTransaction.controller.destroy();
    }
  }
}
