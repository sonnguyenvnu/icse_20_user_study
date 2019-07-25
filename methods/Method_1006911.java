/** 
 * Add a partitioner which can be used to create a  {@link StepExecutionSplitter}. Use either this or an explicit {@link #splitter(StepExecutionSplitter)} but not both.
 * @param slaveStepName the name of the slave step (used to construct step execution names)
 * @param partitioner a partitioner to use
 * @return this for fluent chaining
 */
public PartitionStepBuilder partitioner(String slaveStepName,Partitioner partitioner){
  this.stepName=slaveStepName;
  this.partitioner=partitioner;
  return this;
}
