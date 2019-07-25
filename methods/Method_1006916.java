/** 
 * Create a partition step builder for a remote (or local) step.
 * @param step the step to execute in parallel
 * @return a PartitionStepBuilder
 */
public PartitionStepBuilder partitioner(Step step){
  return new PartitionStepBuilder(this).step(step);
}
