/** 
 * Returns a builder for a property having as value a list of  {@code <T>}. The format of the individual items is the same as for  {@linkplain #enumProperty(String,Map)}.
 * @param name        Name of the property to build
 * @param nameToValue Map of labels to values. The null key is ignored.
 * @param < T >         Value type of the property
 * @return A new builder
 */
public static <T>GenericCollectionPropertyBuilder<T,List<T>> enumListProperty(String name,Map<String,T> nameToValue){
  return enumProperty(name,nameToValue).toList().delim(MultiValuePropertyDescriptor.DEFAULT_DELIMITER);
}
