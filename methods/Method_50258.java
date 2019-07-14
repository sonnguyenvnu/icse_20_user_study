private void subscribeEvent(){
  Disposable subscriptionOpenMediaPreviewEvent=RxBus.getDefault().toObservable(OpenMediaPreviewFragmentEvent.class).map(mediaPreviewEvent -> mediaPreviewEvent).subscribeWith(new RxBusDisposable<OpenMediaPreviewFragmentEvent>(){
    @Override protected void onEvent(    OpenMediaPreviewFragmentEvent openMediaPreviewFragmentEvent){
      mPreviewPosition=0;
      showMediaPreviewFragment();
    }
  }
);
  RxBus.getDefault().add(subscriptionOpenMediaPreviewEvent);
  Disposable subscriptionMediaCheckChangeEvent=RxBus.getDefault().toObservable(MediaCheckChangeEvent.class).map(mediaCheckChangeEvent -> mediaCheckChangeEvent).subscribeWith(new RxBusDisposable<MediaCheckChangeEvent>(){
    @Override protected void onEvent(    MediaCheckChangeEvent mediaCheckChangeEvent){
      MediaBean mediaBean=mediaCheckChangeEvent.getMediaBean();
      if (mCheckedList.contains(mediaBean)) {
        mCheckedList.remove(mediaBean);
      }
 else {
        mCheckedList.add(mediaBean);
      }
      if (mCheckedList.size() > 0) {
        String text=getResources().getString(R.string.gallery_over_button_text_checked,mCheckedList.size(),mConfiguration.getMaxSize());
        mTvOverAction.setText(text);
        mTvOverAction.setEnabled(true);
      }
 else {
        mTvOverAction.setText(R.string.gallery_over_button_text);
        mTvOverAction.setEnabled(false);
      }
    }
  }
);
  RxBus.getDefault().add(subscriptionMediaCheckChangeEvent);
  Disposable subscriptionMediaViewPagerChangedEvent=RxBus.getDefault().toObservable(MediaViewPagerChangedEvent.class).map(mediaViewPagerChangedEvent -> mediaViewPagerChangedEvent).subscribeWith(new RxBusDisposable<MediaViewPagerChangedEvent>(){
    @Override protected void onEvent(    MediaViewPagerChangedEvent mediaPreviewViewPagerChangedEvent){
      int curIndex=mediaPreviewViewPagerChangedEvent.getCurIndex();
      int totalSize=mediaPreviewViewPagerChangedEvent.getTotalSize();
      if (mediaPreviewViewPagerChangedEvent.isPreview()) {
        mPreviewPosition=curIndex;
      }
 else {
        mPagePosition=curIndex;
      }
      String title=getString(R.string.gallery_page_title,curIndex + 1,totalSize);
      mTvToolbarTitle.setText(title);
    }
  }
);
  RxBus.getDefault().add(subscriptionMediaViewPagerChangedEvent);
  Disposable subscriptionCloseRxMediaGridPageEvent=RxBus.getDefault().toObservable(CloseRxMediaGridPageEvent.class).subscribeWith(new RxBusDisposable<CloseRxMediaGridPageEvent>(){
    @Override protected void onEvent(    CloseRxMediaGridPageEvent closeRxMediaGridPageEvent) throws Exception {
      finish();
    }
  }
);
  RxBus.getDefault().add(subscriptionCloseRxMediaGridPageEvent);
  Disposable subscriptionOpenMediaPageFragmentEvent=RxBus.getDefault().toObservable(OpenMediaPageFragmentEvent.class).subscribeWith(new RxBusDisposable<OpenMediaPageFragmentEvent>(){
    @Override protected void onEvent(    OpenMediaPageFragmentEvent openMediaPageFragmentEvent){
      mPageMediaList=openMediaPageFragmentEvent.getMediaBeanList();
      mPagePosition=openMediaPageFragmentEvent.getPosition();
      showMediaPageFragment(mPageMediaList,mPagePosition);
    }
  }
);
  RxBus.getDefault().add(subscriptionOpenMediaPageFragmentEvent);
}
