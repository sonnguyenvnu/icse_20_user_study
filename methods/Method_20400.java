private static boolean hasExplicitDefault(AttributeInfo attribute){
  if (attribute.getCodeToSetDefault().getExplicit() != null) {
    return true;
  }
  if (attribute instanceof ViewAttributeInfo) {
    return ((ViewAttributeInfo)attribute).getHasDefaultKotlinValue();
  }
  return false;
}
