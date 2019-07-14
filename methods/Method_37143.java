/** 
 * @return An observable start loading a card
 */
public Observable<Card> observeCardLoading(){
  if (mLoadCardObservable == null) {
    mLoadCardObservable=Observable.create(new ObservableOnSubscribe<Card>(){
      @Override public void subscribe(      ObservableEmitter<Card> emitter) throws Exception {
        mLoadCardObserver=emitter;
      }
    }
).filter(new Predicate<Card>(){
      @Override public boolean test(      Card card) throws Exception {
        return !card.loading && !card.loaded;
      }
    }
).doOnNext(new Consumer<Card>(){
      @Override public void accept(      Card card) throws Exception {
        card.loading=true;
      }
    }
).doOnDispose(new Action(){
      @Override public void run() throws Exception {
        mLoadCardObserver=null;
      }
    }
);
  }
  return mLoadCardObservable;
}
