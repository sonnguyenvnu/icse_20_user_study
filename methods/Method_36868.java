/** 
 * Combine with  {@link #observeCardLoadingMore()}, use this method as success consumer to subscribe to the Observable.<br /> If your request success, provide a  {@link LoadMoreOp} with original card, non-empty List<BaseCell> and boolean value to indicate whether there is more.<br />Otherwise, provide a  {@link LoadMoreOp} with original card, empty List<BaseCell> and boolean value to indicate whether there is more.
 * @return A consumer to consume load more event.
 */
public Consumer<LoadMoreOp> asDoLoadMoreFinishConsumer(){
  return new Consumer<LoadMoreOp>(){
    @Override public void accept(    LoadMoreOp result) throws Exception {
      Card card=result.getArg1();
      card.loading=false;
      card.loaded=true;
      List<BaseCell> cells=result.getArg2();
      boolean hasMore=result.getArg3();
      if (cells != null && !cells.isEmpty()) {
        if (card.page == sInitialPage) {
          card.setCells(cells);
        }
 else {
          card.addCells(cells);
        }
        card.page++;
        card.hasMore=hasMore;
        card.notifyDataChange();
      }
 else {
        card.hasMore=hasMore;
      }
    }
  }
;
}
