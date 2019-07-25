/** 
 * This method gets a state from the queue, generates all the possible next states of the state, checks the invariants, and updates the state set and state queue.
 */
public void run(){
  TLCGlobals.incNumWorkers();
  TLCStateVec[] newStates=null;
  LongVec[] newFps=null;
  final IStateQueue stateQueue=this.tlcServer.stateQueue;
  try {
    START:     while (true) {
      states=selector.getBlocks(stateQueue,worker);
      if (states == null) {
synchronized (this.tlcServer) {
          this.tlcServer.setDone();
          this.tlcServer.notify();
        }
        stateQueue.finishAll();
        return;
      }
      if (states.length == 0) {
        continue;
      }
      sentStates+=states.length;
      boolean workDone=false;
      while (!workDone) {
        try {
          final NextStateResult res=this.worker.getNextStates(states);
          newStates=res.getNextStates();
          receivedStates+=newStates[0].size();
          newFps=res.getNextFingerprints();
          workDone=true;
          task.setLastInvocation(System.currentTimeMillis());
          tlcServer.addStatesGeneratedDelta(res.getStatesComputedDelta());
        }
 catch (        RemoteException e) {
          if (isRecoverable(e) && states.length > 1) {
            MP.printMessage(EC.TLC_DISTRIBUTED_EXCEED_BLOCKSIZE,Integer.toString(states.length / 2));
            stateQueue.sEnqueue(states);
            selector.setMaxTXSize(states.length / 2);
            continue START;
          }
 else {
            MP.printMessage(EC.TLC_DISTRIBUTED_WORKER_LOST,getUri().toString());
            handleRemoteWorkerLost(stateQueue);
            return;
          }
        }
catch (        NullPointerException e) {
          MP.printMessage(EC.TLC_DISTRIBUTED_WORKER_LOST,"\n" + throwableToString(e));
          handleRemoteWorkerLost(stateQueue);
          return;
        }
      }
      BitVector[] visited=this.tlcServer.fpSetManager.putBlock(newFps,executorService);
      for (int i=0; i < visited.length; i++) {
        BitVector.Iter iter=new BitVector.Iter(visited[i]);
        int index;
        while ((index=iter.next()) != -1) {
          TLCState state=newStates[i].elementAt(index);
          long fp=newFps[i].elementAt(index);
          state.uid=this.tlcServer.trace.writeState(state,fp);
          stateQueue.sEnqueue(state);
        }
      }
    }
  }
 catch (  Throwable e) {
    TLCState state1=null, state2=null;
    if (e instanceof WorkerException) {
      state1=((WorkerException)e).state1;
      state2=((WorkerException)e).state2;
    }
    if (this.tlcServer.setErrState(state1,true)) {
      if (state1 != null) {
        try {
          this.tlcServer.trace.printTrace(state1,state2);
        }
 catch (        Exception e1) {
          MP.printError(EC.GENERAL,e1);
        }
      }
 else {
        MP.printError(EC.GENERAL,e);
      }
      stateQueue.finishAll();
synchronized (this.tlcServer) {
        this.tlcServer.notify();
      }
    }
  }
 finally {
    try {
      cacheRateHitRatio=worker.getCacheRateRatio();
    }
 catch (    RemoteException e) {
      MP.printWarning(EC.GENERAL,"Failed to read remote worker cache statistic (Expect to see a negative chache hit rate. Does not invalidate model checking results)");
    }
    keepAliveTimer.cancel();
    states=new TLCState[0];
  }
}
