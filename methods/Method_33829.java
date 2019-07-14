/** 
 * ???????????????
 * @param gankIoDataBean ???
 */
private void handleImageList(GankIoDataBean gankIoDataBean){
  Observable.create(new ObservableOnSubscribe<ArrayList<ArrayList<String>>>(){
    @Override public void subscribe(    ObservableEmitter<ArrayList<ArrayList<String>>> emitter) throws Exception {
      imgList.clear();
      imageTitleList.clear();
      for (int i=0; i < gankIoDataBean.getResults().size(); i++) {
        imgList.add(gankIoDataBean.getResults().get(i).getUrl());
        imageTitleList.add(gankIoDataBean.getResults().get(i).getDesc());
      }
      allList.clear();
      allList.add(imgList);
      allList.add(imageTitleList);
      emitter.onNext(allList);
    }
  }
).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ArrayList<ArrayList<String>>>(){
    @Override public void onSubscribe(    Disposable d){
      addDisposable(d);
    }
    @Override public void onNext(    ArrayList<ArrayList<String>> arrayLists){
      allListData.setValue(arrayLists);
    }
    @Override public void onError(    Throwable e){
      allListData.setValue(null);
    }
    @Override public void onComplete(){
    }
  }
);
}
