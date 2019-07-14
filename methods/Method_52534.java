/** 
 * Subtypeability rules. https://docs.oracle.com/javase/specs/jls/se7/html/jls-4.html#jls-4.10
 */
public static boolean isSubtypeable(JavaTypeDefinition parameter,JavaTypeDefinition argument){
  if (argument.getType() == null) {
    return true;
  }
  if (parameter.getType().isAssignableFrom(argument.getType())) {
    if (!parameter.isGeneric() || parameter.isRawType() || argument.isRawType()) {
      return true;
    }
    JavaTypeDefinition argSuper=argument.getAsSuper(parameter.getType());
    return parameter.getType().equals(argSuper.getType());
  }
  int indexOfParameter=PRIMITIVE_SUBTYPE_ORDER.indexOf(parameter.getType());
  if (indexOfParameter != -1) {
    if (argument.getType() == char.class) {
      if (indexOfParameter <= 3) {
        return true;
      }
    }
 else {
      int indexOfArg=PRIMITIVE_SUBTYPE_ORDER.indexOf(argument.getType());
      if (indexOfArg != -1 && indexOfParameter <= indexOfArg) {
        return true;
      }
    }
  }
  return false;
}
