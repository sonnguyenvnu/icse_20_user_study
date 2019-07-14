/** 
 * Add a constraint on which vertices the given edge label can connect using the schema manager.
 * @param edgeLabel to which the constraint applies.
 * @param outVLabel specifies the outgoing vertex for this connection.
 * @param inVLabel specifies the incoming vertex for this connection.
 * @param manager is used to update the schema.
 * @see org.janusgraph.core.schema.SchemaManager
 */
default void makeConnectionConstraint(EdgeLabel edgeLabel,VertexLabel outVLabel,VertexLabel inVLabel,SchemaManager manager){
  manager.addConnection(edgeLabel,outVLabel,inVLabel);
}
