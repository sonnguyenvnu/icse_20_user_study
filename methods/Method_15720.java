@PostConstruct public void init(){
  users.forEach((id,properties) -> {
    if (StringUtils.isEmpty(properties.getId())) {
      properties.setId(id);
    }
    for (    EmbedAuthenticationProperties.PermissionInfo permissionInfo : properties.getPermissions()) {
      for (      Map<String,Object> objectMap : permissionInfo.getDataAccesses()) {
        for (        Map.Entry<String,Object> stringObjectEntry : objectMap.entrySet()) {
          if (stringObjectEntry.getValue() instanceof Map) {
            Map mapVal=((Map)stringObjectEntry.getValue());
            boolean maybeIsList=mapVal.keySet().stream().allMatch(org.hswebframework.utils.StringUtils::isInt);
            if (maybeIsList) {
              stringObjectEntry.setValue(mapVal.values());
            }
          }
        }
      }
    }
    authentications.put(id,properties.toAuthentication(dataAccessConfigBuilderFactory));
  }
);
}
