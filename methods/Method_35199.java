/** 
 * Returns the hosted Controller that was pushed with the given tag or  {@code null} if nosuch Controller exists in this Router.
 * @param tag The tag being searched for
 */
@Nullable public Controller getControllerWithTag(@NonNull String tag){
  for (  RouterTransaction transaction : backstack) {
    if (tag.equals(transaction.tag())) {
      return transaction.controller;
    }
  }
  return null;
}
