/** 
 * This method should be called after any code that may trigger a child view to cause a call to {@link RecyclerView#requestLayout()}. <p> A call to this method must always be accompanied by a call to {@link #startInterceptRequestLayout()} that precedes the code that may trigger a childView to cause a call to  {@link RecyclerView#requestLayout()}.
 * @see #startInterceptRequestLayout()
 */
void stopInterceptRequestLayout(boolean performLayoutChildren){
  if (mInterceptRequestLayoutDepth < 1) {
    if (DEBUG) {
      throw new IllegalStateException("stopInterceptRequestLayout was called more " + "times than startInterceptRequestLayout." + exceptionLabel());
    }
    mInterceptRequestLayoutDepth=1;
  }
  if (!performLayoutChildren && !mLayoutSuppressed) {
    mLayoutWasDefered=false;
  }
  if (mInterceptRequestLayoutDepth == 1) {
    if (performLayoutChildren && mLayoutWasDefered && !mLayoutSuppressed && mLayout != null && mAdapter != null) {
      dispatchLayout();
    }
    if (!mLayoutSuppressed) {
      mLayoutWasDefered=false;
    }
  }
  mInterceptRequestLayoutDepth--;
}
