/** 
 * Adds a unidirectional "uses" style relationship between this element and the specified element.
 * @param destination      the target of the relationship
 * @param description      a description of the relationship (e.g. "uses", "gets data from", "sends data to")
 * @param technology       the technology details (e.g. JSON/HTTPS)
 * @param interactionStyle the interaction style (sync vs async)
 * @return the relationship that has just been created and added to the model
 */
@Nullable public Relationship uses(@Nonnull StaticStructureElement destination,String description,String technology,InteractionStyle interactionStyle){
  return getModel().addRelationship(this,destination,description,technology,interactionStyle);
}
