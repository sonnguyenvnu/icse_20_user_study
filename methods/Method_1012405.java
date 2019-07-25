public void search(String content){
  iView.showLoading(null);
  StringBuilder stringBuilder=new StringBuilder();
  stringBuilder.append("http://s.video.qq.com/smartbox?num=10&otype=json&query=").append(content);
  baseModel.getRepositoryManager().getApi(VideoApi.class).searchVideo(stringBuilder.toString()).subscribeOn(Schedulers.io()).flatMap((Function<ResponseBody,ObservableSource<SearchVideoBean>>)responseBody -> {
    String body=responseBody.string().replace("QZOutputJson=","");
    SearchVideoBean searchVideoBean=BaseApplication.getAppComponent().getGson().fromJson(body.substring(0,body.length() - 1),SearchVideoBean.class);
    return Observable.just(searchVideoBean);
  }
).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<SearchVideoBean>(){
    @Override public void onSubscribe(    Disposable d){
      addDispose(d);
    }
    @Override public void onNext(    SearchVideoBean searchVideoBean){
      BaseBean baseBean=new BaseBean();
      baseBean.setCode(200);
      baseBean.setType(VideoUtil.BASE_TYPE_SEARCH_CONTENT);
      baseBean.setData(searchVideoBean);
      iView.updateData(baseBean);
    }
    @Override public void onError(    Throwable e){
      iView.showError(e.getMessage(),null);
    }
    @Override public void onComplete(){
      iView.hideLoading();
    }
  }
);
}
