/** 
 * Add property constraints for a given vertex label using the schema manager.
 * @param vertexLabel to which the constraint applies.
 * @param key defines the property which should be added to the vertex label as a constraint.
 * @param manager is used to update the schema.
 * @see org.janusgraph.core.schema.SchemaManager
 */
default void makePropertyConstraintForVertex(VertexLabel vertexLabel,PropertyKey key,SchemaManager manager){
  manager.addProperties(vertexLabel,key);
}
