@Override public void addFolders(List<File> folders,final List<Folder> existedFolders){
  Subscription subscription=Observable.from(folders).filter(new Func1<File,Boolean>(){
    @Override public Boolean call(    File file){
      for (      Folder folder : existedFolders) {
        if (file.getAbsolutePath().equals(folder.getPath())) {
          return false;
        }
      }
      return true;
    }
  }
).flatMap(new Func1<File,Observable<Folder>>(){
    @Override public Observable<Folder> call(    File file){
      Folder folder=new Folder();
      folder.setName(file.getName());
      folder.setPath(file.getAbsolutePath());
      List<Song> musicFiles=FileUtils.musicFiles(file);
      folder.setSongs(musicFiles);
      folder.setNumOfSongs(musicFiles.size());
      return Observable.just(folder);
    }
  }
).toList().flatMap(new Func1<List<Folder>,Observable<List<Folder>>>(){
    @Override public Observable<List<Folder>> call(    List<Folder> folders){
      return mRepository.create(folders);
    }
  }
).doOnNext(new Action1<List<Folder>>(){
    @Override public void call(    List<Folder> folders){
      Collections.sort(folders,new Comparator<Folder>(){
        @Override public int compare(        Folder f1,        Folder f2){
          return f1.getName().compareToIgnoreCase(f2.getName());
        }
      }
);
    }
  }
).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<Folder>>(){
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
    @Override public void onNext(    List<Folder> allNewFolders){
      mView.onFoldersAdded(allNewFolders);
    }
  }
);
  mSubscriptions.add(subscription);
}
