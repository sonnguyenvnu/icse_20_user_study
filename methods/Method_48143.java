/** 
 * Add property constraints for a given edge label using the schema manager.
 * @param edgeLabel to which the constraint applies.
 * @param key defines the property which should be added to the edge label as a constraint.
 * @param manager is used to update the schema.
 * @see org.janusgraph.core.schema.SchemaManager
 */
default void makePropertyConstraintForEdge(EdgeLabel edgeLabel,PropertyKey key,SchemaManager manager){
  manager.addProperties(edgeLabel,key);
}
