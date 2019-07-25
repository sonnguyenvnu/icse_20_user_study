/** 
 * Adds a relationship between this and another deployment node.
 * @param destination       the destination DeploymentNode
 * @param description       a short description of the relationship
 * @param technology        the technology
 * @param interactionStyle  the interation style (Synchronous vs Asynchronous)
 * @return                  a Relationship object
 */
public Relationship uses(DeploymentNode destination,String description,String technology,InteractionStyle interactionStyle){
  return getModel().addRelationship(this,destination,description,technology,interactionStyle);
}
