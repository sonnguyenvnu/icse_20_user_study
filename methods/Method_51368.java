protected static String[] legalPackageNamesIn(Map<PropertyDescriptorField,String> valuesById,char delimiter){
  String names=valuesById.get(LEGAL_PACKAGES);
  return StringUtils.isBlank(names) ? null : StringUtils.split(names,delimiter);
}
