/** 
 * @ExcludeFromJavadoc
 */
@Override public HystrixDynamicProperty<Integer> getInteger(String name,Integer fallback){
  return new IntegerDynamicProperty(name,fallback);
}
