/** 
 * Create a new Builder with the start Data object and its  {@link DataSchema}.
 * @param object provides the start Data object.
 * @param schema provides the {@link DataSchema} of the start Data object.
 * @return a {@link Builder}.
 */
private static Builder create(Object object,DataSchema schema){
  return create(object,schema,IterationOrder.PRE_ORDER);
}
