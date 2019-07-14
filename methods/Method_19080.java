private void requestFocus(final String sectionKey,final int index){
  focusRequestOnUiThread(mMainThreadHandler,new Runnable(){
    @Override public void run(){
      final SectionLocationInfo sectionLocationInfo=findSectionForKey(sectionKey);
      if (isFocusValid(sectionLocationInfo,index)) {
        mFocusDispatcher.requestFocus(sectionLocationInfo.mStartIndex + index);
      }
    }
  }
);
}
