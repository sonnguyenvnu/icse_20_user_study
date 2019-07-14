public void incrementTotalCallsTime(int networkType,int value){
  callsTotalTime[networkType]+=value;
  saveStats();
}
