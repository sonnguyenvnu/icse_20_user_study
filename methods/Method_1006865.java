/** 
 * Continue or end a job with this step if the previous step was successful.
 * @param step a step to execute next
 * @return this for fluent chaining
 */
public SimpleJobBuilder next(Step step){
  steps.add(step);
  return this;
}
