/** 
 * RxBus
 */
private void subscribeEvent(){
  mMediaCheckChangeDisposable=RxBus.getDefault().toObservable(MediaCheckChangeEvent.class).subscribeWith(new RxBusDisposable<MediaCheckChangeEvent>(){
    @Override protected void onEvent(    MediaCheckChangeEvent mediaCheckChangeEvent){
      if (mMediaActivity.getCheckedList().size() == 0) {
        mTvPreview.setEnabled(false);
      }
 else {
        mTvPreview.setEnabled(true);
      }
    }
  }
);
  RxBus.getDefault().add(mMediaCheckChangeDisposable);
  mCloseMediaViewPageFragmentDisposable=RxBus.getDefault().toObservable(CloseMediaViewPageFragmentEvent.class).subscribeWith(new RxBusDisposable<CloseMediaViewPageFragmentEvent>(){
    @Override protected void onEvent(    CloseMediaViewPageFragmentEvent closeMediaViewPageFragmentEvent) throws Exception {
      mMediaGridAdapter.notifyDataSetChanged();
    }
  }
);
  RxBus.getDefault().add(mCloseMediaViewPageFragmentDisposable);
  mRequestStorageReadAccessPermissionDisposable=RxBus.getDefault().toObservable(RequestStorageReadAccessPermissionEvent.class).subscribeWith(new RxBusDisposable<RequestStorageReadAccessPermissionEvent>(){
    @Override protected void onEvent(    RequestStorageReadAccessPermissionEvent requestStorageReadAccessPermissionEvent) throws Exception {
      if (requestStorageReadAccessPermissionEvent.getType() == RequestStorageReadAccessPermissionEvent.TYPE_WRITE) {
        if (requestStorageReadAccessPermissionEvent.isSuccess()) {
          mMediaGridPresenter.getMediaList(mBucketId,mPage,LIMIT);
        }
 else {
          getActivity().finish();
        }
      }
 else {
        if (requestStorageReadAccessPermissionEvent.isSuccess()) {
          openCamera(mMediaActivity);
        }
      }
    }
  }
);
  RxBus.getDefault().add(mRequestStorageReadAccessPermissionDisposable);
}
