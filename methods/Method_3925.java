private void initChildrenHelper(){
  mChildHelper=new ChildHelper(new ChildHelper.Callback(){
    @Override public int getChildCount(){
      return RecyclerView.this.getChildCount();
    }
    @Override public void addView(    View child,    int index){
      if (VERBOSE_TRACING) {
        TraceCompat.beginSection("RV addView");
      }
      RecyclerView.this.addView(child,index);
      if (VERBOSE_TRACING) {
        TraceCompat.endSection();
      }
      dispatchChildAttached(child);
    }
    @Override public int indexOfChild(    View view){
      return RecyclerView.this.indexOfChild(view);
    }
    @Override public void removeViewAt(    int index){
      final View child=RecyclerView.this.getChildAt(index);
      if (child != null) {
        dispatchChildDetached(child);
        child.clearAnimation();
      }
      if (VERBOSE_TRACING) {
        TraceCompat.beginSection("RV removeViewAt");
      }
      RecyclerView.this.removeViewAt(index);
      if (VERBOSE_TRACING) {
        TraceCompat.endSection();
      }
    }
    @Override public View getChildAt(    int offset){
      return RecyclerView.this.getChildAt(offset);
    }
    @Override public void removeAllViews(){
      final int count=getChildCount();
      for (int i=0; i < count; i++) {
        View child=getChildAt(i);
        dispatchChildDetached(child);
        child.clearAnimation();
      }
      RecyclerView.this.removeAllViews();
    }
    @Override public ViewHolder getChildViewHolder(    View view){
      return getChildViewHolderInt(view);
    }
    @Override public void attachViewToParent(    View child,    int index,    ViewGroup.LayoutParams layoutParams){
      final ViewHolder vh=getChildViewHolderInt(child);
      if (vh != null) {
        if (!vh.isTmpDetached() && !vh.shouldIgnore()) {
          throw new IllegalArgumentException("Called attach on a child which is not" + " detached: " + vh + exceptionLabel());
        }
        if (DEBUG) {
          Log.d(TAG,"reAttach " + vh);
        }
        vh.clearTmpDetachFlag();
      }
      RecyclerView.this.attachViewToParent(child,index,layoutParams);
    }
    @Override public void detachViewFromParent(    int offset){
      final View view=getChildAt(offset);
      if (view != null) {
        final ViewHolder vh=getChildViewHolderInt(view);
        if (vh != null) {
          if (vh.isTmpDetached() && !vh.shouldIgnore()) {
            throw new IllegalArgumentException("called detach on an already" + " detached child " + vh + exceptionLabel());
          }
          if (DEBUG) {
            Log.d(TAG,"tmpDetach " + vh);
          }
          vh.addFlags(ViewHolder.FLAG_TMP_DETACHED);
        }
      }
      RecyclerView.this.detachViewFromParent(offset);
    }
    @Override public void onEnteredHiddenState(    View child){
      final ViewHolder vh=getChildViewHolderInt(child);
      if (vh != null) {
        vh.onEnteredHiddenState(RecyclerView.this);
      }
    }
    @Override public void onLeftHiddenState(    View child){
      final ViewHolder vh=getChildViewHolderInt(child);
      if (vh != null) {
        vh.onLeftHiddenState(RecyclerView.this);
      }
    }
  }
);
}
