/** 
 * @ExcludeFromJavadoc
 */
@Override public HystrixDynamicProperty<Boolean> getBoolean(String name,Boolean fallback){
  return new BooleanDynamicProperty(name,fallback);
}
