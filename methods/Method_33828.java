public MutableLiveData<GankIoDataBean> loadWelfareData(){
  final MutableLiveData<GankIoDataBean> data=new MutableLiveData<>();
  mModel.setData("??",mPage,HttpUtils.per_page_more);
  mModel.getGankIoData(new RequestImpl(){
    @Override public void loadSuccess(    Object object){
      GankIoDataBean gankIoDataBean=(GankIoDataBean)object;
      handleImageList(gankIoDataBean);
      data.setValue(gankIoDataBean);
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
