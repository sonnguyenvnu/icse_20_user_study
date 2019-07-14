/** 
 * {@inheritDoc}
 */
@Override public void topPosition(BaseCell cell){
  if (cell != null) {
    int pos=mGroupBasicAdapter.getComponents().indexOf(cell);
    if (pos > 0) {
      VirtualLayoutManager lm=getLayoutManager();
      View view=lm.findViewByPosition(pos);
      if (view != null) {
        int top=lm.getDecoratedTop(view);
        RecyclerView recyclerView=getContentView();
        if (recyclerView != null) {
          recyclerView.scrollBy(0,top);
        }
      }
 else {
        RecyclerView recyclerView=getContentView();
        if (recyclerView != null) {
          recyclerView.scrollToPosition(pos);
        }
      }
    }
  }
}
