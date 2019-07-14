public MutableLiveData<Boolean> register(){
  final MutableLiveData<Boolean> data=new MutableLiveData<>();
  if (!verifyData()) {
    data.setValue(false);
    return data;
  }
  HttpClient.Builder.getWanAndroidServer().register(username.get(),password.get(),password.get()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<LoginBean>(){
    @Override public void onSubscribe(    Disposable d){
      addDisposable(d);
    }
    @Override public void onNext(    LoginBean bean){
      if (bean != null && bean.getData() != null) {
        Injection.get().addData(bean.getData());
        UserUtil.handleLoginSuccess();
        data.setValue(true);
      }
 else {
        if (bean != null) {
          ToastUtil.showToastLong(bean.getErrorMsg());
        }
        data.setValue(false);
      }
    }
    @Override public void onError(    Throwable e){
      data.setValue(false);
    }
    @Override public void onComplete(){
    }
  }
);
  return data;
}
