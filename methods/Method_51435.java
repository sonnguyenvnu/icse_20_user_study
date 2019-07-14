/** 
 * Returns a builder for a property having as value a list of long integers. The format of the individual items is the same as for  {@linkplain #longIntProperty(String)} longIntProperty}.
 * @param name Name of the property to build
 * @return A new builder
 */
public static GenericCollectionPropertyBuilder<Long,List<Long>> longIntListProperty(String name){
  return longIntProperty(name).toList().delim(MultiValuePropertyDescriptor.DEFAULT_NUMERIC_DELIMITER);
}
