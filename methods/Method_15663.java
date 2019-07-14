@PostConstruct public void init(){
  if (defaultSupportConvert.contains(FIELD_SCOPE)) {
    converts.add(createJsonConfig(FIELD_SCOPE,SimpleFiledScopeDataAccessConfig.class));
  }
  if (defaultSupportConvert.contains(DENY_FIELDS)) {
    converts.add(createJsonConfig(DENY_FIELDS,SimpleFieldFilterDataAccessConfig.class));
  }
  if (defaultSupportConvert.contains(OWN_CREATED)) {
    converts.add(createConfig(OWN_CREATED,(action,config) -> new SimpleOwnCreatedDataAccessConfig(action)));
  }
  if (defaultSupportConvert.contains(SCRIPT)) {
    converts.add(createJsonConfig(SCRIPT,SimpleScriptDataAccessConfig.class));
  }
  if (defaultSupportConvert.contains(CUSTOM)) {
    converts.add(createConfig(CUSTOM,(action,config) -> new SimpleCustomDataAccessConfigConfig(config)));
  }
}
