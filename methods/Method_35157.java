@Override public void setBackstack(@NonNull List<RouterTransaction> newBackstack,@Nullable ControllerChangeHandler changeHandler){
  if (isDetachFrozen) {
    for (    RouterTransaction transaction : newBackstack) {
      transaction.controller.setDetachFrozen(true);
    }
  }
  super.setBackstack(newBackstack,changeHandler);
}
