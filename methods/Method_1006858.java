/** 
 * Create a new job builder that will execute a step or sequence of steps.
 * @param step a step to execute
 * @return a {@link SimpleJobBuilder}
 */
public JobFlowBuilder flow(Step step){
  return new FlowJobBuilder(this).start(step);
}
