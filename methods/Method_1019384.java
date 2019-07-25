/** 
 * Adds a unidirectional "uses" style relationship between this element and component.
 * @param destination the target of the relationship
 * @param description a description of the relationship (e.g. "uses", "gets data from", "sends data to")
 * @return the relationship that has just been created and added to the model
 */
@Nullable public Relationship uses(@Nonnull Component destination,String description){
  return uses(destination,description,null);
}
