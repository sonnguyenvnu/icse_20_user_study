public Observable<Integer> observeScrollStateChanged(String id){
  List<BannerListener> list=mScrollStateListenerArrayMap.get(id);
  if (list == null) {
    list=new ArrayList<>();
    mScrollStateListenerArrayMap.put(id,list);
  }
  RxBannerScrollStateChangedListener rxBannerSelectedListener=new RxBannerScrollStateChangedListener();
  list.add(rxBannerSelectedListener);
  BannerScrollStateChangedObservable bannerSupportObservable=new BannerScrollStateChangedObservable(rxBannerSelectedListener);
  return bannerSupportObservable;
}
