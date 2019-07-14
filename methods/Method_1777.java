private void updateMaxHeaderLength(){
  mMaxHeaderLength=mDefaultFormatChecker.getHeaderSize();
  if (mCustomImageFormatCheckers != null) {
    for (    ImageFormat.FormatChecker checker : mCustomImageFormatCheckers) {
      mMaxHeaderLength=Math.max(mMaxHeaderLength,checker.getHeaderSize());
    }
  }
}
