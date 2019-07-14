/** 
 * {@inheritDoc}
 */
@Override public void scrollToPosition(BaseCell cell){
  if (cell != null) {
    int pos=mGroupBasicAdapter.getComponents().indexOf(cell);
    if (pos > 0) {
      RecyclerView recyclerView=getContentView();
      if (recyclerView != null) {
        recyclerView.scrollToPosition(pos);
      }
    }
  }
}
