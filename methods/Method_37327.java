/** 
 * Retrieves converter for provided type. Only registered types are matched, therefore subclasses must be also registered.
 * @return founded converter or <code>null</code>
 */
public <T>TypeConverter<T> lookup(final Class<T> type){
  return converters.get(type);
}
