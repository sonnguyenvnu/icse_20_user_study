/** 
 * Returns a builder for a property having as value a list of characters. The format of the individual items is the same as for  {@linkplain #charProperty(String) charProperty}.
 * @param name Name of the property to build
 * @return A new builder
 */
public static GenericCollectionPropertyBuilder<Character,List<Character>> charListProperty(String name){
  return charProperty(name).toList();
}
