public MutableLiveData<GankIoDataBean> loadGankData(){
  final MutableLiveData<GankIoDataBean> data=new MutableLiveData<>();
  mModel.setData(mType,mPage,HttpUtils.per_page_more);
  mModel.getGankIoData(new RequestImpl(){
    @Override public void loadSuccess(    Object object){
      data.setValue((GankIoDataBean)object);
    }
    @Override public void loadFailed(){
      if (mPage > 1) {
        mPage--;
      }
      data.setValue(null);
    }
    @Override public void addSubscription(    Disposable disposable){
      addDisposable(disposable);
    }
  }
);
  return data;
}
