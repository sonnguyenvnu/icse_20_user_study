public void setResetStatEnable(boolean resetStatEnable){
  this.resetStatEnable=resetStatEnable;
  if (dataSourceStat != null) {
    dataSourceStat.setResetStatEnable(resetStatEnable);
  }
}
