/** 
 * Returns  {@code true} if this chunk is an endpoint and therefore bound tothe configuration.
 */
public boolean isEndpoint(){
  return actionRuntime != null;
}
