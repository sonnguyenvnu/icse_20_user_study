/** 
 * Method to find value to inject, and inject it to this property.
 */
public void inject(DeserializationContext context,Object beanInstance) throws IOException {
  set(beanInstance,findInjectableValue(context,beanInstance));
}
