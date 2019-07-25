/** 
 * Start a job with this step, but expect to transition from there to other flows or steps.
 * @param step the step to start with
 * @return a builder to enable fluent chaining
 */
public JobFlowBuilder start(Step step){
  return new JobFlowBuilder(this,step);
}
