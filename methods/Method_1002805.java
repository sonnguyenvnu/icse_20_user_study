/** 
 * Returns a newly-created  {@link SimpleCompositeService} based on the {@link Service}s added to this builder.
 */
public SimpleCompositeService<I,O> build(){
  return new SimpleCompositeService<>(services());
}
