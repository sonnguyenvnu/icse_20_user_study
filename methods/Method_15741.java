private boolean handleEvent(AuthorizingContext context,HandleType type){
  if (null != eventPublisher) {
    AuthorizingHandleBeforeEvent event=new AuthorizingHandleBeforeEvent(context,type);
    eventPublisher.publishEvent(event);
    if (!event.isExecute()) {
      if (event.isAllow()) {
        return true;
      }
 else {
        throw new AccessDenyException(event.getMessage());
      }
    }
  }
  return false;
}
