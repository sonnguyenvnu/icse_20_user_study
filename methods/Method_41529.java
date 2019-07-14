/** 
 * Create a JobBuilder with which to define a <code>JobDetail</code>, and set the class name of the <code>Job</code> to be executed.
 * @return a new JobBuilder
 */
public static JobBuilder newJob(Class<? extends Job> jobClass){
  JobBuilder b=new JobBuilder();
  b.ofType(jobClass);
  return b;
}
