/** 
 * Returns a builder for a property having as value a list of strings. The format of the individual items is the same as for  {@linkplain #stringProperty(String) stringProperty}.
 * @param name Name of the property to build
 * @return A new builder
 */
public static GenericCollectionPropertyBuilder<String,List<String>> stringListProperty(String name){
  return stringProperty(name).toList();
}
