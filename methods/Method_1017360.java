/** 
 * Build container which stores all service routes
 * @param routes Unfiltered routes
 */
@NotNull public static ServiceRouteContainer build(@NotNull Map<String,Route> routes){
  Collection<Route> serviceRoutes=new ArrayList<>();
  for (  Route route : routes.values()) {
    String controller=route.getController();
    if (controller == null || !RouteHelper.isServiceController(controller)) {
      continue;
    }
    String[] split=controller.split(":");
    if (split.length > 1) {
      serviceRoutes.add(route);
    }
  }
  return new ServiceRouteContainer(serviceRoutes);
}
