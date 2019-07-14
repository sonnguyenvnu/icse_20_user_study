public List<String> getGroupConfigurationDataIds(){
  List<String> groupConfigurationDataIds=new ArrayList<>();
  if (StringUtils.isEmpty(applicationGroup)) {
    return groupConfigurationDataIds;
  }
  String[] parts=applicationGroup.split("\\.");
  for (int i=1; i < parts.length; i++) {
    StringBuilder subGroup=new StringBuilder(parts[0]);
    for (int j=1; j <= i; j++) {
      subGroup.append(".").append(parts[j]);
    }
    groupConfigurationDataIds.add(subGroup + ":application." + acmProperties.getFileExtension());
  }
  return groupConfigurationDataIds;
}
