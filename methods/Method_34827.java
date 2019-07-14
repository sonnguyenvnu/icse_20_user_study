/** 
 * @ExcludeFromJavadoc
 */
@Override public HystrixDynamicProperty<String> getString(String name,String fallback){
  return new StringDynamicProperty(name,fallback);
}
