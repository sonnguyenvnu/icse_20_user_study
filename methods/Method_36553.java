public static String generateSofaReferenceBeanName(Class<?> interfaceType,String uniqueId){
  if (StringUtils.isEmpty(uniqueId)) {
    return REFERENCE_BEAN_NAME_PREFIX + interfaceType.getCanonicalName();
  }
  return REFERENCE_BEAN_NAME_PREFIX + interfaceType.getCanonicalName() + ":" + uniqueId;
}
