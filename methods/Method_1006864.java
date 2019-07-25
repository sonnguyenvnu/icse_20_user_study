/** 
 * Start with this decider. Returns a flow builder and when the flow is ended a job builder will be returned to continue the job configuration if needed.
 * @param decider a decider to execute first
 * @return builder for fluent chaining
 */
public JobFlowBuilder start(JobExecutionDecider decider){
  if (builder == null) {
    builder=new JobFlowBuilder(new FlowJobBuilder(this),decider);
  }
 else {
    builder.start(decider);
  }
  if (!steps.isEmpty()) {
    steps.remove(0);
  }
  for (  Step step : steps) {
    builder.next(step);
  }
  return builder;
}
