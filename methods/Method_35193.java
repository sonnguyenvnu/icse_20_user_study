/** 
 * This should be called by the host Activity when its onBackPressed method is called. The call will be forwarded to its top  {@link Controller}. If that controller doesn't handle it, then it will be popped.
 * @return Whether or not a back action was handled by the Router
 */
@UiThread public boolean handleBack(){
  ThreadUtils.ensureMainThread();
  if (!backstack.isEmpty()) {
    if (backstack.peek().controller.handleBack()) {
      return true;
    }
 else     if (popCurrentController()) {
      return true;
    }
  }
  return false;
}
