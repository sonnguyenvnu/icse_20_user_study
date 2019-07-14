public void resetStats(int networkType){
  resetStatsDate[networkType]=System.currentTimeMillis();
  for (int a=0; a < TYPES_COUNT; a++) {
    sentBytes[networkType][a]=0;
    receivedBytes[networkType][a]=0;
    sentItems[networkType][a]=0;
    receivedItems[networkType][a]=0;
  }
  callsTotalTime[networkType]=0;
  saveStats();
}
