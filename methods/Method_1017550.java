private void decide(SecurityRule rule,Transition<S,E> object){
  Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
  Collection<ConfigAttribute> configAttributes=getTransitionConfigAttributes(rule);
  if (transitionAccessDecisionManager != null) {
    decide(transitionAccessDecisionManager,authentication,object,configAttributes);
  }
 else {
    decide(createDefaultTransitionManager(rule),authentication,object,configAttributes);
  }
}
