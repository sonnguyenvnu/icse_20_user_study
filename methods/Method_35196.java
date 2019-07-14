/** 
 * Pops all  {@link Controller}s until the  {@link Controller} with the passed tag is at the top
 * @param tag           The tag being popped to
 * @param changeHandler The {@link ControllerChangeHandler} to handle this transaction
 * @return Whether or not the {@link Controller} with the passed tag is now at the top
 */
@SuppressWarnings("WeakerAccess") @UiThread public boolean popToTag(@NonNull String tag,@Nullable ControllerChangeHandler changeHandler){
  ThreadUtils.ensureMainThread();
  for (  RouterTransaction transaction : backstack) {
    if (tag.equals(transaction.tag())) {
      popToTransaction(transaction,changeHandler);
      return true;
    }
  }
  return false;
}
