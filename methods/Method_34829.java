/** 
 * @ExcludeFromJavadoc
 */
@Override public HystrixDynamicProperty<Long> getLong(String name,Long fallback){
  return new LongDynamicProperty(name,fallback);
}
