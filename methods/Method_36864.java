/** 
 * Start to load data for a card, usually called by  {@link com.tmall.wireless.tangram.TangramEngine}
 * @param card the card need reactively loading data
 */
public void reactiveDoLoad(Card card){
  if (mLoadCardObserver == null) {
    return;
  }
  mLoadCardObserver.onNext(card);
}
