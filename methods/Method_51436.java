/** 
 * Returns a builder for a property having as value a list of decimal numbers. The format of the individual items is the same as for  {@linkplain #doubleProperty(String) doubleProperty}.
 * @param name Name of the property to build
 * @return A new builder
 */
public static GenericCollectionPropertyBuilder<Double,List<Double>> doubleListProperty(String name){
  return doubleProperty(name).toList().delim(MultiValuePropertyDescriptor.DEFAULT_NUMERIC_DELIMITER);
}
