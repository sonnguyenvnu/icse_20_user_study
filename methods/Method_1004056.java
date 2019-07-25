/** 
 * Increments the counters by the values given by another element.
 * @param child counters to add
 */
public void increment(final ICoverageNode child){
  instructionCounter=instructionCounter.increment(child.getInstructionCounter());
  branchCounter=branchCounter.increment(child.getBranchCounter());
  lineCounter=lineCounter.increment(child.getLineCounter());
  complexityCounter=complexityCounter.increment(child.getComplexityCounter());
  methodCounter=methodCounter.increment(child.getMethodCounter());
  classCounter=classCounter.increment(child.getClassCounter());
}
