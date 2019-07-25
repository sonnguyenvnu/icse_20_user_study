/** 
 * Adds a unidirectional relationship between this element and a person.
 * @param destination the target of the relationship
 * @param description a description of the relationship (e.g. "sends e-mail to")
 * @param technology  the technology details (e.g. JSON/HTTPS)
 * @return the relationship that has just been created and added to the model
 */
@Nullable public Relationship delivers(@Nonnull Person destination,String description,String technology){
  return delivers(destination,description,technology,InteractionStyle.Synchronous);
}
