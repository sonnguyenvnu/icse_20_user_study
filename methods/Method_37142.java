/** 
 * start load data for a card, usually called by  {@link TangramEngine}
 * @param card the card need async loading data
 */
public void doLoad(final Card card){
  if (mAsyncLoader == null) {
    return;
  }
  if (!card.loading && !card.loaded) {
    card.loading=true;
    mAsyncLoader.loadData(card,new AsyncLoader.LoadedCallback(){
      @Override public void finish(){
        card.loading=false;
        card.loaded=true;
      }
      @Override public void finish(      List<BaseCell> cells){
        finish();
        card.addCells(cells);
        card.notifyDataChange();
      }
      public void fail(      boolean loaded){
        card.loading=false;
        card.loaded=loaded;
      }
    }
);
  }
}
