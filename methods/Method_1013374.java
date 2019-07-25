public final boolean recover() throws IOException {
  boolean recovered=false;
  if (this.fromChkpt != null) {
    MP.printMessage(EC.TLC_CHECKPOINT_RECOVER_START,this.fromChkpt);
    this.trace.recover();
    this.theStateQueue.recover();
    this.theFPSet.recover(this.trace);
    if (this.checkLiveness) {
      this.tool.getInitStates(new IStateFunctor(){
        public Object addElement(        TLCState state){
          liveCheck.addInitState(tool,state,state.fingerPrint());
          return true;
        }
      }
);
      liveCheck.recover();
    }
    MP.printMessage(EC.TLC_CHECKPOINT_RECOVER_END,new String[]{String.valueOf(this.theFPSet.size()),String.valueOf(this.theStateQueue.size())});
    recovered=true;
    numberOfInitialStates=this.theFPSet.size();
  }
  return recovered;
}
