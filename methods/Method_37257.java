/** 
 * Converter to collection.
 */
@SuppressWarnings("unchecked") protected Object convertToCollection(final Object value,final Class destinationType,final Class componentType){
  return typeConverterManager.convertToCollection(value,destinationType,componentType);
}
