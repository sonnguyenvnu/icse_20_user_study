private void logScan(QualityInfo qualityInfo,boolean isFinalImage){
  mDebugOutput.append(String.format(Locale.getDefault(),"%s: %s, goodEnough=%b, fullQuality=%b, quality=%d\n\n",mDateFormat.format(new Date(System.currentTimeMillis())),isFinalImage ? "final" : "intermediate",qualityInfo.isOfGoodEnoughQuality(),qualityInfo.isOfFullQuality(),qualityInfo.getQuality()));
  mDebugOutputScrollView.post(new Runnable(){
    @Override public void run(){
      mDebugOutputScrollView.scrollTo(0,mDebugOutputScrollView.getBottom());
    }
  }
);
}
