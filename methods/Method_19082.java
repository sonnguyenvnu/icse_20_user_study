void requestSmoothFocus(final String globalKey,final int index,final int offset,final SmoothScrollAlignmentType type){
  focusRequestOnUiThread(mMainThreadHandler,new Runnable(){
    @Override public void run(){
      final SectionLocationInfo sectionLocationInfo=findSectionForKey(globalKey);
      if (isFocusValid(sectionLocationInfo,index)) {
        mFocusDispatcher.requestSmoothFocus(sectionLocationInfo.mStartIndex + index,offset,type);
      }
    }
  }
);
}
