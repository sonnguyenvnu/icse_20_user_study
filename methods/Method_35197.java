/** 
 * Sets the root Controller. If any  {@link Controller}s are currently in the backstack, they will be removed.
 * @param transaction The transaction detailing what should be pushed, including the {@link Controller}, and its push and pop  {@link ControllerChangeHandler}, and its tag.
 */
@UiThread public void setRoot(@NonNull RouterTransaction transaction){
  ThreadUtils.ensureMainThread();
  List<RouterTransaction> transactions=Collections.singletonList(transaction);
  setBackstack(transactions,transaction.pushChangeHandler());
}
