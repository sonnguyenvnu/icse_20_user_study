/** 
 * Convert an input value to its string representation. Valid input type includes <ul> <li>Java primitive types and their boxed versions</li> <li>Java Enums</li> <li>Custom classes that have their coercers registered</li> </ul>
 * @param object provides the input value to be coerced.
 * @param fromClass class to be coerced from
 * @return string representation of the input object.
 */
public static String stringify(Object object,Class<?> fromClass){
  if (fromClass == null) {
    fromClass=object.getClass();
  }
  if (object instanceof ByteString) {
    return ((ByteString)object).asAvroString();
  }
  if (DataTemplateUtil.hasCoercer(fromClass)) {
    @SuppressWarnings("unchecked") final Class<Object> clazz=(Class<Object>)fromClass;
    Object coerced=DataTemplateUtil.coerceInput(object,clazz,Object.class);
    if (coerced instanceof ByteString) {
      return ((ByteString)coerced).asAvroString();
    }
 else {
      return coerced.toString();
    }
  }
  return object.toString();
}
