/** 
 * Finds the first innermost sequence e.g IFStart &amp; IFEnd. If the list has been exhausted (firstIndex==lastIndex) the method returns true.
 */
public boolean run(){
  LOGGER.entering(this.getClass().getCanonicalName(),"run");
  this.aktStatus=root;
  this.firstIndex=0;
  this.lastIndex=0;
  boolean lookAhead=false;
  int maximumIterations=this.bracesList.size() * this.bracesList.size();
  int l=-1;
  int i=0;
  while (i < this.bracesList.size()) {
    l++;
    StackObject so=bracesList.get(i);
    if (LOGGER.isLoggable(Level.FINEST)) {
      LOGGER.finest("Processing bracesList(l,i)=(" + l + "," + i + ") of Type " + so.getType() + " with State (aktStatus) = " + aktStatus);
      LOGGER.finest("DataFlowNode @ line " + so.getDataFlowNode().getLine() + " and index=" + so.getDataFlowNode().getIndex());
    }
    aktStatus=this.aktStatus.step(so.getType());
    if (LOGGER.isLoggable(Level.FINEST)) {
      LOGGER.finest("Transition aktStatus=" + aktStatus);
    }
    if (aktStatus == null) {
      if (lookAhead) {
        this.lastIndex=i - 1;
        LOGGER.finer("aktStatus is NULL (lookAhead): Invalid transition");
        LOGGER.exiting(this.getClass().getCanonicalName(),"run",false);
        return false;
      }
 else       if (l > maximumIterations) {
        if (LOGGER.isLoggable(Level.SEVERE)) {
          LOGGER.severe("aktStatus is NULL: maximum Iterations exceeded, abort " + i);
        }
        LOGGER.exiting(this.getClass().getCanonicalName(),"run",false);
        return false;
      }
 else {
        this.aktStatus=root;
        this.firstIndex=i;
        i--;
        if (LOGGER.isLoggable(Level.FINEST)) {
          LOGGER.finest("aktStatus is NULL: Restarting search continue i==" + i + ", firstIndex=" + this.firstIndex);
        }
      }
    }
 else {
      if (aktStatus.isLastStep() && !aktStatus.hasMoreSteps()) {
        this.lastIndex=i;
        if (LOGGER.isLoggable(Level.FINEST)) {
          LOGGER.finest("aktStatus is NOT NULL: lastStep reached and no moreSteps - firstIndex=" + firstIndex + ", lastIndex=" + lastIndex);
        }
        LOGGER.exiting(this.getClass().getCanonicalName(),"run",false);
        return false;
      }
 else       if (aktStatus.isLastStep() && aktStatus.hasMoreSteps()) {
        lookAhead=true;
        this.lastIndex=i;
      }
    }
    i++;
  }
  if (LOGGER.isLoggable(Level.FINEST)) {
    LOGGER.finer("Completed search: firstIndex=" + firstIndex + ", lastIndex=" + lastIndex);
  }
  LOGGER.exiting(this.getClass().getCanonicalName(),"run",this.firstIndex == this.lastIndex);
  return this.firstIndex == this.lastIndex;
}
