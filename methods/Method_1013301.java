public final boolean recover() throws IOException {
  boolean recovered=false;
  if (this.fromChkpt != null) {
    MP.printMessage(EC.TLC_CHECKPOINT_RECOVER_START,this.fromChkpt);
    this.theFPSet.recover();
    if (this.checkLiveness) {
      liveCheck.recover();
    }
    MP.printMessage(EC.TLC_CHECKPOINT_RECOVER_END_DFID,String.valueOf(this.theFPSet.size()));
    recovered=true;
    this.numOfGenStates.set(this.theFPSet.size());
  }
  return recovered;
}
