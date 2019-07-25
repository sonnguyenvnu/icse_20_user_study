/** 
 * Start a job with this flow, but expect to transition from there to other flows or steps.
 * @param flow the flow to start with
 * @return a builder to enable fluent chaining
 */
public JobFlowBuilder start(Flow flow){
  return new JobFlowBuilder(this,flow);
}
