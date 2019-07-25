/** 
 * Creates a job builder and initializes its job repository. Note that if the builder is used to create a &#64;Bean definition then the name of the job and the bean name might be different.
 * @param name the name of the job
 * @return a job builder
 */
public JobBuilder get(String name){
  JobBuilder builder=new JobBuilder(name).repository(jobRepository);
  return builder;
}
