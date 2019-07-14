/** 
 * Combine with  {@link #observeCardLoading()}, use this method as success consumer to subscribe to the Observable.<br /> If your request success, provide a  {@link LoadGroupOp} with original card and non-empty List<BaseCell>.<br />Otherwise, provide a  {@link LoadGroupOp} with original card and empty List<BaseCell>.
 * @return A consumer to consume load event.
 */
public Consumer<LoadGroupOp> asDoLoadFinishConsumer(){
  return new Consumer<LoadGroupOp>(){
    @Override public void accept(    LoadGroupOp result) throws Exception {
      Card card=result.getArg1();
      card.loading=false;
      List<BaseCell> cells=result.getArg2();
      if (cells != null && !cells.isEmpty()) {
        card.loaded=true;
        card.setCells(cells);
        card.notifyDataChange();
      }
 else {
        card.loaded=false;
      }
    }
  }
;
}
