private void replace(int startPosition,int deleteHeaderNumber,int addHeaderNumber,RelativeSizeSpan relativeSizeSpan){
  if (relativeSizeSpan.getSizeChange() == mRxMDConfiguration.getHeader1RelativeSize()) {
    deleteHeaderKey(startPosition,1);
    mRxMDEditText.getText().removeSpan(relativeSizeSpan);
    if (deleteHeaderNumber != 1) {
      addHeaderKey(startPosition,addHeaderNumber);
    }
  }
 else   if (relativeSizeSpan.getSizeChange() == mRxMDConfiguration.getHeader2RelativeSize()) {
    deleteHeaderKey(startPosition,2);
    mRxMDEditText.getText().removeSpan(relativeSizeSpan);
    if (deleteHeaderNumber != 2) {
      addHeaderKey(startPosition,addHeaderNumber);
    }
  }
 else   if (relativeSizeSpan.getSizeChange() == mRxMDConfiguration.getHeader3RelativeSize()) {
    deleteHeaderKey(startPosition,3);
    mRxMDEditText.getText().removeSpan(relativeSizeSpan);
    if (deleteHeaderNumber != 3) {
      addHeaderKey(startPosition,addHeaderNumber);
    }
  }
 else   if (relativeSizeSpan.getSizeChange() == mRxMDConfiguration.getHeader4RelativeSize()) {
    deleteHeaderKey(startPosition,4);
    mRxMDEditText.getText().removeSpan(relativeSizeSpan);
    if (deleteHeaderNumber != 4) {
      addHeaderKey(startPosition,addHeaderNumber);
    }
  }
 else   if (relativeSizeSpan.getSizeChange() == mRxMDConfiguration.getHeader5RelativeSize()) {
    deleteHeaderKey(startPosition,5);
    mRxMDEditText.getText().removeSpan(relativeSizeSpan);
    if (deleteHeaderNumber != 5) {
      addHeaderKey(startPosition,addHeaderNumber);
    }
  }
 else   if (relativeSizeSpan.getSizeChange() == mRxMDConfiguration.getHeader6RelativeSize()) {
    deleteHeaderKey(startPosition,6);
    mRxMDEditText.getText().removeSpan(relativeSizeSpan);
    if (deleteHeaderNumber != 6) {
      addHeaderKey(startPosition,addHeaderNumber);
    }
  }
}
