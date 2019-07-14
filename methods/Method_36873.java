public Observable<Integer> observeSelected(String id){
  List<BannerListener> list=mSelectedListenerArrayMap.get(id);
  if (list == null) {
    list=new ArrayList<>();
    mSelectedListenerArrayMap.put(id,list);
  }
  RxBannerSelectedListener rxBannerSelectedListener=new RxBannerSelectedListener();
  list.add(rxBannerSelectedListener);
  BannerSelectedObservable bannerSupportObservable=new BannerSelectedObservable(rxBannerSelectedListener);
  return bannerSupportObservable;
}
