/** 
 * Creates a  {@link RemoteChunkingMasterStepBuilder} and initializes its jobrepository and transaction manager.
 * @param name the name of the step
 * @param < I > type of input items
 * @param < O > type of output items
 * @return a {@link RemoteChunkingMasterStepBuilder}
 */
public <I,O>RemoteChunkingMasterStepBuilder<I,O> get(String name){
  return new RemoteChunkingMasterStepBuilder<I,O>(name).repository(this.jobRepository).transactionManager(this.transactionManager);
}
