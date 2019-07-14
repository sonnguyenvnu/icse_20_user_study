/** 
 * ?????????????
 * @param customRouters ???Router
 * @return ????
 */
private static HashSet<String> parseExcludeRouter(List<Router> customRouters){
  HashSet<String> excludeKeys=new HashSet<String>();
  if (CommonUtils.isNotEmpty(customRouters)) {
    for (    Router router : customRouters) {
      if (router instanceof ExcludeRouter) {
        ExcludeRouter excludeRouter=(ExcludeRouter)router;
        String excludeName=excludeRouter.getExcludeName();
        if (StringUtils.isNotEmpty(excludeName)) {
          String excludeRouterName=startsWithExcludePrefix(excludeName) ? excludeName.substring(1) : excludeName;
          if (StringUtils.isNotEmpty(excludeRouterName)) {
            excludeKeys.add(excludeRouterName);
          }
        }
        customRouters.remove(router);
      }
    }
  }
  if (!excludeKeys.isEmpty()) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Find exclude routers: {}",excludeKeys);
    }
  }
  return excludeKeys;
}
