public MutableLiveData<GankIoDataBean> loadGankData(String keyWord){
  final MutableLiveData<GankIoDataBean> data=new MutableLiveData<>();
  Disposable subscribe=HttpClient.Builder.getGankIOServer().searchGank(gankPage,mType,keyWord).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<GankIoDataBean>(){
    @Override public void accept(    GankIoDataBean bean) throws Exception {
      if (bean == null || bean.getResults() == null || bean.getResults().size() <= 0) {
        data.setValue(null);
      }
 else {
        data.setValue(bean);
      }
    }
  }
,new Consumer<Throwable>(){
    @Override public void accept(    Throwable throwable) throws Exception {
      if (mPage > 1) {
        mPage--;
      }
      data.setValue(null);
    }
  }
);
  addDisposable(subscribe);
  return data;
}
