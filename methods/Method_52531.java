/** 
 * Method invocation conversion rules. https://docs.oracle.com/javase/specs/jls/se7/html/jls-5.html#jls-5.3
 */
public static boolean isMethodConvertible(JavaTypeDefinition parameter,JavaTypeDefinition argument){
  if (isSubtypeable(parameter,argument)) {
    return true;
  }
  int indexInPrimitive=PRIMITIVE_SUBTYPE_ORDER.indexOf(argument.getType());
  if (indexInPrimitive != -1 && isSubtypeable(parameter,JavaTypeDefinition.forClass(BOXED_PRIMITIVE_SUBTYPE_ORDER.get(indexInPrimitive)))) {
    return true;
  }
  int indexInBoxed=BOXED_PRIMITIVE_SUBTYPE_ORDER.indexOf(argument.getType());
  return indexInBoxed != -1 && isSubtypeable(parameter,JavaTypeDefinition.forClass(PRIMITIVE_SUBTYPE_ORDER.get(indexInBoxed)));
}
