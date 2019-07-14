private void newScanOrImageEndFound(int offset){
  if (mNextFullScanNumber > 0) {
    mBestScanEndOffset=offset;
  }
  mBestScanNumber=mNextFullScanNumber++;
}
