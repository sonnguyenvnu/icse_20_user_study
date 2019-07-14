/** 
 * Start to load data for a card, usually called by  {@link TangramEngine}
 * @param card the card need reactively loading data
 */
public void reactiveDoLoad(Card card){
  if (mLoadCardObserver == null) {
    return;
  }
  mLoadCardObserver.onNext(card);
}
