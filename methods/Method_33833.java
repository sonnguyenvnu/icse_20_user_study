public MutableLiveData<ComingFilmBean> getComingFilm(){
  final MutableLiveData<ComingFilmBean> data=new MutableLiveData<>();
  Disposable subscribe=HttpClient.Builder.getMtimeServer().getComingFilm().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ComingFilmBean>(){
    @Override public void accept(    ComingFilmBean bookBean) throws Exception {
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
