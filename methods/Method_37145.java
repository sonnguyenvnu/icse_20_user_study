/** 
 * @return An observable start loading more for a card
 */
public Observable<Card> observeCardLoadingMore(){
  if (mLoadMoreCardObservable == null) {
    mLoadMoreCardObservable=Observable.create(new ObservableOnSubscribe<Card>(){
      @Override public void subscribe(      ObservableEmitter<Card> emitter) throws Exception {
        mLoadMoreObserver=emitter;
      }
    }
).filter(new Predicate<Card>(){
      @Override public boolean test(      Card card) throws Exception {
        return !card.loading && card.loadMore && card.hasMore;
      }
    }
).doOnNext(new Consumer<Card>(){
      @Override public void accept(      Card card) throws Exception {
        card.loading=true;
        if (!card.loaded) {
          card.page=sInitialPage;
        }
      }
    }
).doOnDispose(new Action(){
      @Override public void run() throws Exception {
        mLoadMoreObserver=null;
      }
    }
);
  }
  return mLoadMoreCardObservable;
}
