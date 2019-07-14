private void performControllerChange(@Nullable RouterTransaction to,@Nullable RouterTransaction from,boolean isPush){
  if (isPush && to != null) {
    to.onAttachedToRouter();
  }
  ControllerChangeHandler changeHandler;
  if (isPush) {
    changeHandler=to.pushChangeHandler();
  }
 else   if (from != null) {
    changeHandler=from.popChangeHandler();
  }
 else {
    changeHandler=null;
  }
  performControllerChange(to,from,isPush,changeHandler);
}
