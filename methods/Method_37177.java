/** 
 * {@inheritDoc}
 */
@Override public void scrollToPosition(Card card){
  List<BaseCell> cells=card.getCells();
  if (cells.size() > 0) {
    BaseCell cell=cells.get(0);
    int pos=mGroupBasicAdapter.getComponents().indexOf(cell);
    if (pos > 0) {
      RecyclerView recyclerView=getContentView();
      if (recyclerView != null) {
        recyclerView.scrollToPosition(pos);
      }
    }
  }
}
