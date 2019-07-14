public MutableLiveData<CollectUrlBean> getCollectUrlList(){
  final MutableLiveData<CollectUrlBean> data=new MutableLiveData<>();
  Disposable subscribe=HttpClient.Builder.getWanAndroidServer().getCollectUrlList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(data::setValue,throwable -> data.setValue(null));
  addDisposable(subscribe);
  return data;
}
