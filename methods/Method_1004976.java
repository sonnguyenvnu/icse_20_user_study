/** 
 * Optimise the provided  {@link Schema} by removing unused types and addingdefault serialisers.
 * @param schema         the schema to optimise
 * @param isStoreOrdered determines whether to enforce ordering in the defaultserialisers or not
 * @return the optimised schema object
 */
public Schema optimise(final Schema schema,final boolean isStoreOrdered){
  if (null != schema && null != schema.getTypes()) {
    return new Schema.Builder().merge(schema).types(getOptimisedTypes(schema,isStoreOrdered)).vertexSerialiser(getDefaultVertexSerialiser(schema,isStoreOrdered)).build();
  }
  return schema;
}
