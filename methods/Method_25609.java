/** 
 * Validates a type string, ensuring it is not generic and not an array type. 
 */
private static void validateTypeStr(String typeStr){
  if (typeStr.contains("[") || typeStr.contains("]")) {
    throw new IllegalArgumentException(String.format("Cannot convert array types (%s), please build them using getType()",typeStr));
  }
  if (typeStr.contains("<") || typeStr.contains(">")) {
    throw new IllegalArgumentException(String.format("Cannot convert generic types (%s), please build them using getType()",typeStr));
  }
}
