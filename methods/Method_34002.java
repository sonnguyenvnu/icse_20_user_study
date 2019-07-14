private List<ConfigAttribute> processAnnotations(Annotation[] annotations){
  if (annotations == null || annotations.length == 0) {
    return null;
  }
  List<ConfigAttribute> attributes=new ArrayList<ConfigAttribute>();
  for (  Annotation a : annotations) {
    if (a instanceof DenyAllConsumers) {
      attributes.add(ConsumerSecurityConfig.DENY_ALL_ATTRIBUTE);
      return attributes;
    }
    if (a instanceof PermitAllConsumers) {
      attributes.add(ConsumerSecurityConfig.PERMIT_ALL_ATTRIBUTE);
      return attributes;
    }
    if (a instanceof ConsumerRolesAllowed) {
      ConsumerRolesAllowed ra=(ConsumerRolesAllowed)a;
      for (      String role : ra.value()) {
        attributes.add(new ConsumerSecurityConfig(role,ConsumerSecurityConfig.ConsumerSecurityType.CONSUMER_ROLE));
      }
      return attributes;
    }
    if (a instanceof ConsumerKeysAllowed) {
      ConsumerKeysAllowed ka=(ConsumerKeysAllowed)a;
      for (      String key : ka.value()) {
        attributes.add(new ConsumerSecurityConfig(key,ConsumerSecurityConfig.ConsumerSecurityType.CONSUMER_KEY));
      }
      return attributes;
    }
  }
  return null;
}
