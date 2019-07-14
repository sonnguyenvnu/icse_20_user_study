/** 
 * Resolves lazy value during the parsing runtime.
 */
private Object resolveLazyValue(Object value){
  if (value instanceof Supplier) {
    value=((Supplier)value).get();
  }
  return value;
}
