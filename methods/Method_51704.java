/** 
 * constructs a predicate from the given attribute
 * @param attribute attribute to be formatted as predicate
 * @return predicate
 */
public static String constructPredicate(Attribute attribute){
  return "[@" + attribute.getName() + '=' + formatValueForXPath(attribute) + ']';
}
