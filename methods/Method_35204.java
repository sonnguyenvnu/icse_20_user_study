private void popToTransaction(@NonNull RouterTransaction transaction,@Nullable ControllerChangeHandler changeHandler){
  if (backstack.size() > 0) {
    RouterTransaction topTransaction=backstack.peek();
    List<RouterTransaction> updatedBackstack=new ArrayList<>();
    Iterator<RouterTransaction> backstackIterator=backstack.reverseIterator();
    while (backstackIterator.hasNext()) {
      RouterTransaction existingTransaction=backstackIterator.next();
      updatedBackstack.add(existingTransaction);
      if (existingTransaction == transaction) {
        break;
      }
    }
    if (changeHandler == null) {
      changeHandler=topTransaction.popChangeHandler();
    }
    setBackstack(updatedBackstack,changeHandler);
  }
}
