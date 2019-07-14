public static String generateSofaServiceBeanName(String interfaceName,String uniqueId){
  if (StringUtils.isEmpty(uniqueId)) {
    return SERVICE_BEAN_NAME_PREFIX + interfaceName;
  }
  return SERVICE_BEAN_NAME_PREFIX + interfaceName + ":" + uniqueId;
}
