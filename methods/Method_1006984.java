/** 
 * Creates a  {@link RemotePartitioningMasterStepBuilder} and initializes its jobrepository, job explorer, bean factory and transaction manager.
 * @param name the name of the step
 * @return a {@link RemotePartitioningMasterStepBuilder}
 */
public RemotePartitioningMasterStepBuilder get(String name){
  return new RemotePartitioningMasterStepBuilder(name).repository(this.jobRepository).jobExplorer(this.jobExplorer).beanFactory(this.beanFactory).transactionManager(this.transactionManager);
}
