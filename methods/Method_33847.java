public MutableLiveData<TreeBean> getTree(){
  final MutableLiveData<TreeBean> data=new MutableLiveData<>();
  Disposable subscribe=HttpClient.Builder.getWanAndroidServer().getTree().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<TreeBean>(){
    @Override public void accept(    TreeBean treeBean) throws Exception {
      data.setValue(treeBean);
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
