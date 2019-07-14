@Override public void loadFolders(){
  Subscription subscription=mRepository.folders().subscribeOn(Schedulers.io()).doOnNext(new Action1<List<Folder>>(){
    @Override public void call(    List<Folder> folders){
      Collections.sort(folders,new Comparator<Folder>(){
        @Override public int compare(        Folder f1,        Folder f2){
          return f1.getName().compareToIgnoreCase(f2.getName());
        }
      }
);
    }
  }
).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<Folder>>(){
    @Override public void onStart(){
      mView.showLoading();
    }
    @Override public void onCompleted(){
      mView.hideLoading();
    }
    @Override public void onError(    Throwable e){
      mView.hideLoading();
      mView.handleError(e);
    }
    @Override public void onNext(    List<Folder> folders){
      mView.onFoldersLoaded(folders);
    }
  }
);
  mSubscriptions.add(subscription);
}
