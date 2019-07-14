/** 
 * Start to load more data for a card, usually called by  {@link com.tmall.wireless.tangram.TangramEngine}
 * @param card the card need reactively loading data
 */
public void reactiveDoLoadMore(Card card){
  if (mLoadMoreObserver == null) {
    return;
  }
  mLoadMoreObserver.onNext(card);
}
