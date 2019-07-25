private void decide(SecurityRule rule,Message<E> object){
  Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
  Collection<ConfigAttribute> configAttributes=getEentConfigAttributes(rule);
  if (eventAccessDecisionManager != null) {
    decide(eventAccessDecisionManager,authentication,object,configAttributes);
  }
 else {
    decide(createDefaultEventManager(rule),authentication,object,configAttributes);
  }
}
