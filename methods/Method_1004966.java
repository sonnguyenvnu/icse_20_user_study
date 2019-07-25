/** 
 * Checks if the graphId with a relationship already exists.
 * @param graphId The GraphId.
 * @return True if a relationship exists.
 */
public boolean exists(final String graphId){
  return null != getIds(graphId);
}
