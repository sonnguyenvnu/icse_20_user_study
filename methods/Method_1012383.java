public RouterResult deal(RouterRequest routerRequest){
  if (routerRequest != null) {
    BaseProvider baseProvider=providerMap.get(routerRequest.getProvideName());
    BaseAction baseAction;
    if (baseProvider != null) {
      baseAction=baseProvider.getAction(routerRequest.getActionName());
      if (baseAction != null) {
        return baseAction.invoke(routerRequest);
      }
    }
  }
  return null;
}
