/** 
 * Start the job with this step.
 * @param step a step to start with
 * @return this for fluent chaining
 */
public SimpleJobBuilder start(Step step){
  if (steps.isEmpty()) {
    steps.add(step);
  }
 else {
    steps.set(0,step);
  }
  return this;
}
