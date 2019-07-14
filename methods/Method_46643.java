public long getDtxLockTime(){
  return dtxLockTime == -1 ? dtxTime : dtxLockTime;
}
