/** 
 * ????
 */
public MutableLiveData<HomeListBean> getCollectList(){
  final MutableLiveData<HomeListBean> data=new MutableLiveData<>();
  Disposable subscribe=HttpClient.Builder.getWanAndroidServer().getCollectList(mPage).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<HomeListBean>(){
    @Override public void accept(    HomeListBean bean) throws Exception {
      data.setValue(bean);
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
