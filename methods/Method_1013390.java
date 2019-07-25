/** 
 * This method gets a state from the queue, generates all the possible next states of the state, checks the invariants, and updates the state set and state queue.
 */
public void run(){
  final boolean checkLiveness=this.tlc.checkLiveness;
  TLCState curState=null;
  try {
    while (true) {
      curState=this.squeue.sDequeue();
      if (curState == null) {
synchronized (this.tlc) {
          this.tlc.setDone();
          this.tlc.notify();
        }
        this.squeue.finishAll();
        return;
      }
      setCurrentState(curState);
      SetOfStates setOfStates=null;
      if (checkLiveness) {
        setOfStates=createSetOfStates();
      }
      if (this.tlc.doNext(curState,setOfStates,this)) {
        return;
      }
      if (checkLiveness) {
        doNextCheckLiveness(curState,setOfStates);
      }
      this.outDegree.addSample(unseenSuccessorStates);
      unseenSuccessorStates=0;
    }
  }
 catch (  Throwable e) {
    resetCurrentState();
synchronized (this.tlc) {
      if (this.tlc.setErrState(curState,null,true,EC.GENERAL)) {
        MP.printError(EC.GENERAL,e);
      }
      this.squeue.finishAll();
      this.tlc.notify();
    }
    return;
  }
}
