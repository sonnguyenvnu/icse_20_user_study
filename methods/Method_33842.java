public MutableLiveData<List<SearchTagBean.DataBean>> getHotkey(){
  final MutableLiveData<List<SearchTagBean.DataBean>> data=new MutableLiveData<>();
  Disposable subscribe=HttpClient.Builder.getWanAndroidServer().getHotkey().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<SearchTagBean>(){
    @Override public void accept(    SearchTagBean bean) throws Exception {
      if (bean == null || bean.getData() == null || bean.getData().size() <= 0) {
        data.setValue(null);
      }
 else {
        data.setValue(bean.getData());
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
