public void requestFocusOnRoot(int index){
  final String sectionKey;
synchronized (this) {
    if (mCurrentSection == null) {
      return;
    }
    sectionKey=mCurrentSection.getGlobalKey();
  }
  requestFocus(sectionKey,index);
}
