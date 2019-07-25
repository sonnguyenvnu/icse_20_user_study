/** 
 * Create a new Builder with the start Data object obtained from the provided  {@link DataTemplate}.
 * @param template that provides the start Data object.
 * @return a {@link Builder}.
 */
private static Builder create(DataTemplate<? extends Object> template){
  return create(template.data(),template.schema(),IterationOrder.PRE_ORDER);
}
