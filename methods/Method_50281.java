@Override public void onScanCompleted(String[] images){
  if (images == null || images.length == 0) {
    Logger.i("images empty");
    return;
  }
  Observable.create((ObservableOnSubscribe<MediaBean>)subscriber -> {
    MediaBean mediaBean=mConfiguration.isImage() ? MediaUtils.getMediaBeanWithImage(getContext(),images[0]) : MediaUtils.getMediaBeanWithVideo(getContext(),images[0]);
    subscriber.onNext(mediaBean);
    subscriber.onComplete();
  }
).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableObserver<MediaBean>(){
    @Override public void onComplete(){
    }
    @Override public void onError(    Throwable e){
      Logger.i("??MediaBean??" + e.toString());
    }
    @Override public void onNext(    MediaBean mediaBean){
      if (!isDetached() && mediaBean != null) {
        int bk=FileUtils.existImageDir(mediaBean.getOriginalPath());
        if (bk != -1) {
          mMediaBeanList.add(1,mediaBean);
          mMediaGridAdapter.notifyDataSetChanged();
        }
 else {
          Logger.i("????");
        }
      }
    }
  }
);
}
