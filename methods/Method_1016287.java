/** 
 * Executes this  {@code Wait} task.
 * @return {@link Status#SUCCEEDED} if the specified timeout has expired; {@link Status#RUNNING} otherwise. 
 */
@Override public Status execute(){
  return GdxAI.getTimepiece().getTime() - startTime < timeout ? Status.RUNNING : Status.SUCCEEDED;
}
