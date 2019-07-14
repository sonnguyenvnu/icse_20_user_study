/** 
 * ??RecyclerView??
 */
public void showRecyclerViewData(final RequestImpl listener){
  SPUtils.putString(HOME_ONE,"");
  SPUtils.putString(HOME_TWO,"");
  SPUtils.putString(HOME_SIX,"");
  Function<GankIoDayBean,Observable<List<List<AndroidBean>>>> function=new Function<GankIoDayBean,Observable<List<List<AndroidBean>>>>(){
    @Override public Observable<List<List<AndroidBean>>> apply(    GankIoDayBean gankIoDayBean) throws Exception {
      List<List<AndroidBean>> lists=new ArrayList<>();
      GankIoDayBean.ResultsBean results=gankIoDayBean.getResults();
      if (results.getAndroid() != null && results.getAndroid().size() > 0) {
        addUrlList(lists,results.getAndroid(),"Android");
      }
      if (results.getWelfare() != null && results.getWelfare().size() > 0) {
        addUrlList(lists,results.getWelfare(),"??");
      }
      if (results.getiOS() != null && results.getiOS().size() > 0) {
        addUrlList(lists,results.getiOS(),"IOS");
      }
      if (results.getRestMovie() != null && results.getRestMovie().size() > 0) {
        addUrlList(lists,results.getRestMovie(),"????");
      }
      if (results.getResource() != null && results.getResource().size() > 0) {
        addUrlList(lists,results.getResource(),"????");
      }
      if (results.getRecommend() != null && results.getRecommend().size() > 0) {
        addUrlList(lists,results.getRecommend(),"???");
      }
      if (results.getFront() != null && results.getFront().size() > 0) {
        addUrlList(lists,results.getFront(),"??");
      }
      if (results.getApp() != null && results.getApp().size() > 0) {
        addUrlList(lists,results.getApp(),"App");
      }
      return Observable.just(lists);
    }
  }
;
  Observer<List<List<AndroidBean>>> observer=new Observer<List<List<AndroidBean>>>(){
    @Override public void onError(    Throwable e){
      listener.loadFailed();
    }
    @Override public void onComplete(){
    }
    @Override public void onSubscribe(    Disposable d){
      listener.addSubscription(d);
    }
    @Override public void onNext(    List<List<AndroidBean>> lists){
      listener.loadSuccess(lists);
    }
  }
;
  HttpClient.Builder.getGankIOServer().getGankIoDay(year,month,day).flatMap(function).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
}
