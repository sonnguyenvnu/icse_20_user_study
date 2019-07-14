public void showQSBKList(final WanNavigator.JokeModelNavigator listener,int page){
  Function<QsbkListBean,Observable<List<DuanZiBean>>> func1=new Function<QsbkListBean,Observable<List<DuanZiBean>>>(){
    @Override public Observable<List<DuanZiBean>> apply(    QsbkListBean bean){
      List<DuanZiBean> lists=new ArrayList<>();
      if (bean != null && bean.getItems() != null && bean.getItems().size() > 0) {
        List<QsbkListBean.ItemsBean> items=bean.getItems();
        for (        QsbkListBean.ItemsBean bean1 : items) {
          DuanZiBean duanZiBean=new DuanZiBean();
          duanZiBean.setContent(bean1.getContent());
          duanZiBean.setCreateTime(bean1.getPublished_at());
          QsbkListBean.ItemsBean.TopicBean topic=bean1.getTopic();
          QsbkListBean.ItemsBean.UserBean user=bean1.getUser();
          if (topic != null) {
            duanZiBean.setCategoryName(topic.getContent());
          }
          if (user != null) {
            duanZiBean.setName(user.getLogin());
            String thumb=user.getThumb();
            if (!TextUtils.isEmpty(thumb)) {
              if (!thumb.contains("http")) {
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append("http:");
                stringBuilder.append(thumb);
                duanZiBean.setAvatarUrl(stringBuilder.toString());
              }
 else {
                duanZiBean.setAvatarUrl(thumb);
              }
            }
          }
          lists.add(duanZiBean);
        }
      }
      return Observable.just(lists);
    }
  }
;
  Observer<List<DuanZiBean>> observer=new Observer<List<DuanZiBean>>(){
    @Override public void onError(    Throwable e){
      listener.loadFailed();
    }
    @Override public void onComplete(){
    }
    @Override public void onSubscribe(    Disposable d){
      listener.addSubscription(d);
    }
    @Override public void onNext(    List<DuanZiBean> lists){
      listener.loadSuccess(lists);
    }
  }
;
  HttpClient.Builder.getQSBKServer().getQsbkList(page).flatMap(func1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
}
