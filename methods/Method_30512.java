public void fixRebroadcasted(boolean rebroadcasted){
  if (isRebroadcasted() != rebroadcasted) {
    if (rebroadcasted) {
      rebroadcastedFix=true;
      ++rebroadcastCount;
    }
 else {
      rebroadcastId=null;
      rebroadcastedFix=false;
      --rebroadcastCount;
    }
  }
}
