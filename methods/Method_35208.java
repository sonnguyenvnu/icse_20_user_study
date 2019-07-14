private void performControllerChange(@Nullable final Controller to,@Nullable final Controller from,final boolean isPush,@Nullable final ControllerChangeHandler changeHandler){
  if (isPush && to != null && to.isDestroyed()) {
    throw new IllegalStateException("Trying to push a controller that has already been destroyed. (" + to.getClass().getSimpleName() + ")");
  }
  final ChangeTransaction transaction=new ChangeTransaction(to,from,isPush,container,changeHandler,new ArrayList<>(changeListeners));
  if (pendingControllerChanges.size() > 0) {
    pendingControllerChanges.add(transaction);
  }
 else   if (from != null && (changeHandler == null || changeHandler.removesFromViewOnPush()) && !containerFullyAttached) {
    pendingControllerChanges.add(transaction);
    container.post(new Runnable(){
      @Override public void run(){
        performPendingControllerChanges();
      }
    }
);
  }
 else {
    ControllerChangeHandler.executeChange(transaction);
  }
}
