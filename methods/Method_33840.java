/** 
 * ??
 */
public MutableLiveData<HomeListBean> searchWan(String keyWord){
  final MutableLiveData<HomeListBean> data=new MutableLiveData<>();
  Disposable subscribe=HttpClient.Builder.getWanAndroidServer().searchWan(mPage,keyWord).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<HomeListBean>(){
    @Override public void accept(    HomeListBean bean) throws Exception {
      if (bean == null || bean.getData() == null || bean.getData().getDatas() == null || bean.getData().getDatas().size() <= 0) {
        data.setValue(null);
      }
 else {
        data.setValue(bean);
      }
    }
  }
,new Consumer<Throwable>(){
    @Override public void accept(    Throwable throwable) throws Exception {
      if (mPage > 0) {
        mPage--;
      }
      data.setValue(null);
    }
  }
);
  addDisposable(subscribe);
  return data;
}
