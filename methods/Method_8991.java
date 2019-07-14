public void cancelClickRunnables(boolean uncheck){
  if (selectChildRunnable != null) {
    AndroidUtilities.cancelRunOnUIThread(selectChildRunnable);
    selectChildRunnable=null;
  }
  if (currentChildView != null) {
    View child=currentChildView;
    if (uncheck) {
      onChildPressed(currentChildView,false);
    }
    currentChildView=null;
    removeSelection(child,null);
  }
  if (clickRunnable != null) {
    AndroidUtilities.cancelRunOnUIThread(clickRunnable);
    clickRunnable=null;
  }
  interceptedByChild=false;
}
