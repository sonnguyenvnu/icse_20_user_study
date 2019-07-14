public String[] packageNamesIn(Map<PropertyDescriptorField,String> params){
  return StringUtils.split(params.get(LEGAL_PACKAGES),PACKAGE_NAME_DELIMITER);
}
