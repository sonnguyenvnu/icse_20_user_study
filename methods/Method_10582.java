private void updateMadeCodeCount(){
  mRxTickerViewMade.setText(RxDataTool.stringToInt(RxSPTool.getContent(mContext,SP_MADE_CODE)) + "",true);
}
