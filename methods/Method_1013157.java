/** 
 * Set the data structures that cause highlighting of changes in the error trace.
 */
public void diff(final TLCState other){
  if (this == other || wasDiffed || other.isStuttering() || other.isBackToState()) {
    return;
  }
  wasDiffed=true;
  final List<TLCVariable> predecessorVariables=this.getVariablesAsList();
  final List<TLCVariable> secondVariables=other.getVariablesAsList();
  for (int i=0; i < predecessorVariables.size(); i++) {
    final TLCVariableValue firstValue=predecessorVariables.get(i).getValue();
    final TLCVariableValue secondValue=secondVariables.get(i).getValue();
    firstValue.diff(secondValue);
  }
}
