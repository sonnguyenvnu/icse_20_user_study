/** 
 * Creates a step builder and initializes its job repository and transaction manager. Note that if the builder is used to create a &#64;Bean definition then the name of the step and the bean name might be different.
 * @param name the name of the step
 * @return a step builder
 */
public StepBuilder get(String name){
  StepBuilder builder=new StepBuilder(name).repository(jobRepository).transactionManager(transactionManager);
  return builder;
}
