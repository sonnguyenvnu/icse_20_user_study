/** 
 * Accessor for iterating over logical properties that the type handled by this serializer has, from serialization perspective. Actual type of properties, if any, will be {@link com.fasterxml.jackson.databind.ser.BeanPropertyWriter}. Of standard Jackson serializers, only  {@link com.fasterxml.jackson.databind.ser.BeanSerializer}exposes properties.
 */
public Iterator<PropertyWriter> properties(){
  return ClassUtil.emptyIterator();
}
