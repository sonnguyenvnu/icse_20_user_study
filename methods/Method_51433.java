/** 
 * Returns a builder for a property having as value a list of integers. The format of the individual items is the same as for  {@linkplain #intProperty(String) intProperty}.
 * @param name Name of the property to build
 * @return A new builder
 */
public static GenericCollectionPropertyBuilder<Integer,List<Integer>> intListProperty(String name){
  return intProperty(name).toList().delim(MultiValuePropertyDescriptor.DEFAULT_NUMERIC_DELIMITER);
}
