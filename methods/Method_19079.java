void requestFocusEnd(final String sectionKey){
  focusRequestOnUiThread(mMainThreadHandler,new Runnable(){
    @Override public void run(){
      final SectionLocationInfo locationInfo=findSectionForKey(sectionKey);
      mFocusDispatcher.requestFocus(locationInfo.mStartIndex + locationInfo.mSection.getCount() - 1);
    }
  }
);
}
