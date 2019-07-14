void requestFocusWithOffset(final String sectionKey,final int index,final int offset){
  focusRequestOnUiThread(mMainThreadHandler,new Runnable(){
    @Override public void run(){
      final SectionLocationInfo sectionLocationInfo=findSectionForKey(sectionKey);
      if (isFocusValid(sectionLocationInfo,index)) {
        mFocusDispatcher.requestFocusWithOffset(sectionLocationInfo.mStartIndex + index,offset);
      }
    }
  }
);
}
