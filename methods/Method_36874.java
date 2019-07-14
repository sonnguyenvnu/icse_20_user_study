public Observable<ScrollEvent> observeScrolled(String id){
  List<BannerListener> list=mScrolledListenerArrayMap.get(id);
  if (list == null) {
    list=new ArrayList<>();
    mScrolledListenerArrayMap.put(id,list);
  }
  RxBannerScrolledListener rxBannerSelectedListener=new RxBannerScrolledListener();
  list.add(rxBannerSelectedListener);
  BannerScrolledObservable bannerSupportObservable=new BannerScrolledObservable(rxBannerSelectedListener);
  return bannerSupportObservable;
}
