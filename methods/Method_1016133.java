/** 
 * {@inheritDoc}
 */
@Override public String provide(final Object object){
  return object != null ? object.toString() : "";
}
