/** 
 * @param qualifiedName is one of the constants from the {@link LithoClassNames}
 * @return short name. For {@link LithoClassNames#EVENT_ANNOTATION_NAME} it would be Event.
 */
public static String shortName(String qualifiedName){
  int indexAfterlastDot=qualifiedName.lastIndexOf('.') + 1;
  if (indexAfterlastDot >= qualifiedName.length()) {
    return qualifiedName;
  }
  return qualifiedName.substring(indexAfterlastDot);
}
