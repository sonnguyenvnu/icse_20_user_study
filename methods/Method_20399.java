private static boolean hasDefaultKotlinValue(AttributeInfo attribute){
  if (attribute instanceof ViewAttributeInfo) {
    return ((ViewAttributeInfo)attribute).getHasDefaultKotlinValue();
  }
  return false;
}
