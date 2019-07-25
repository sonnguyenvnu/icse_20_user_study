/** 
 * Create a new Builder with the start Data object and its  {@link DataSchema}.
 * @param object provides the start Data object.
 * @param schema provides the {@link DataSchema} of the start Data object.
 * @param order provides the iteration order.
 * @return a {@link Builder}.
 */
public static Builder create(Object object,DataSchema schema,IterationOrder order){
  return new Builder(new SimpleDataElement(object,schema),order);
}
