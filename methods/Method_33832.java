public MutableLiveData<MtimeFilmeBean> getHotFilm(){
  final MutableLiveData<MtimeFilmeBean> data=new MutableLiveData<>();
  Disposable subscribe=HttpClient.Builder.getMtimeServer().getHotFilm().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<MtimeFilmeBean>(){
    @Override public void accept(    MtimeFilmeBean bookBean) throws Exception {
      data.setValue(bookBean);
    }
  }
,new Consumer<Throwable>(){
    @Override public void accept(    Throwable throwable) throws Exception {
      data.setValue(null);
    }
  }
);
  addDisposable(subscribe);
  return data;
}
