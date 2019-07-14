public MutableLiveData<HotMovieBean> getComingSoon(){
  final MutableLiveData<HotMovieBean> data=new MutableLiveData<>();
  Disposable subscribe=HttpClient.Builder.getDouBanService().getComingSoon(mStart,mCount).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<HotMovieBean>(){
    @Override public void accept(    HotMovieBean hotMovieBean) throws Exception {
      data.setValue(hotMovieBean);
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
